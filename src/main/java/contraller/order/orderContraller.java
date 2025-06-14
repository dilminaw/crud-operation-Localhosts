package contraller.order;

import contraller.item.itemContraller;
import dbConnection.dbconnection;
import javafx.scene.control.Alert;
import model.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class orderContraller {
    Connection connection = dbconnection.getInstance().getConnection();

    public orderContraller() throws SQLException {
    }

    public boolean placeOrder(order orders) throws SQLException {
        try {

            String sql="insert into productsales values (?,?,?)";
            connection.setAutoCommit(false);
            PreparedStatement prepared = connection.prepareStatement(sql);
            prepared.setObject(1, orders.getOrderId());
            prepared.setObject(2,orders.getOrderDate());
            prepared.setObject(3,orders.getCusId());
            boolean isaddOrder = prepared.executeUpdate() > 0;
            if (isaddOrder){
                boolean isaddOrderDeatail=  new orederDetailContraller().addOrderDetail(orders.getOrderDeatails());
                if (isaddOrderDeatail){
                    boolean updateqty=   itemContraller.getInstance().updateStock(orders.getOrderDeatails());
                    if (updateqty){
                        new Alert(Alert.AlertType.CONFIRMATION,"updated").show();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;

        }finally {
            connection.setAutoCommit(true);
        }


    }

}
