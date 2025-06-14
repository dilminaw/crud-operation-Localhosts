package contraller.item;

import dbConnection.dbconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.item;
import model.orderDeatails;
import util.Crudutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class itemContraller implements itemService {

  itemContraller(){}
     private static itemContraller Instance;

    public static itemContraller getInstance(){
       return null==Instance?  Instance=new itemContraller():Instance;
    }

    @Override
    public boolean addItem(item itm) {
        String sql = "insert into items values(?,?,?,?,?,?,?,?)";
        boolean b;
        try {


            Crudutil.execute(sql, itm.getId(),
                    itm.getName(),
                    itm.getCategory(),
                    itm.getPrice(),
                    itm.getQuantity(),
                    itm.getSuplier(),

                    itm.getLocation(),
                    itm.getDate()

            );

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean deleteItem(String id) {
        String sql="DELETE FROM items WHERE ItemID=?";
        try {

           return Crudutil.execute(sql,id);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean updateItem(item itm) {
        String Sql = "UPDATE items set ItemName=?,Category=?,Price=?,Quantity=?,Supplier=?,WarehouseLocation=?,AddedDate=? WHERE ItemID=?";
        try {
         return    Crudutil.execute(Sql,itm.getName(),itm.getCategory(),
                    itm.getPrice(),itm.getQuantity(),itm.getSuplier(), itm.getLocation(),
                    itm.getDate(),itm.getId());



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public item searchCoustomer(String id) {
        String sql = "SELECT * FROM items WHERE ItemID=?";

        try {

            ResultSet resultSet = Crudutil.execute(sql,id);


            while (resultSet.next()) {
                return new item(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getDate(8).toLocalDate());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }


        return null;
    }

    public ObservableList<item> getAllItems(){
        ObservableList<item> observableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = Crudutil.execute("SELECT * FROM items");
           while ( resultSet.next()){
               observableList.add( new item(resultSet.getString(1),
                       resultSet.getString(2),
                       resultSet.getString(3),
                       resultSet.getDouble(4),
                       resultSet.getInt(5),
                       resultSet.getString(6),
                       resultSet.getString(7),
                       resultSet.getDate(8).toLocalDate()
               ));

           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return observableList;
    }
@Override
    public List<String> getAllitemIds(){
        ArrayList<String> itemIDS = new ArrayList<>();
        ObservableList<item> allItems = getAllItems();
        allItems.forEach(obj->{
            itemIDS.add(obj.getId());

        });
        return itemIDS;
    }

    @Override
    public boolean updateStock(List<orderDeatails> orderDeatails) {

        for (orderDeatails orderDeatails1:orderDeatails){
           boolean isupdate= updateStock(orderDeatails1);
           if (!isupdate){
               return false;
           }
        }
return true;

    }
public boolean updateStock(orderDeatails orderDeatails){
    String sql="update orderdetail set OrderQTY=OrderQTY-? where OrderID=?";
    try {
      return Crudutil.execute(sql,orderDeatails.getQty(),orderDeatails.getItemCode());
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}
}


