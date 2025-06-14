package contraller.order;

import model.orderDeatails;
import util.Crudutil;

import java.sql.SQLException;
import java.util.List;

public class orederDetailContraller {
    public boolean addOrderDetail(List<orderDeatails> orderDetail){
        for (orderDeatails orderdetail: orderDetail){
         boolean addorderDetail=   addOrderDetail(orderdetail);
         if (addorderDetail){
             return true;
         }
        }
return false;
    }
    public boolean addOrderDetail(orderDeatails orderDeatails){
        String sql="insert into orderdetail values (?,?,?,?)";
        try {
           return Crudutil.execute(sql,orderDeatails.getId(),
                    orderDeatails.getItemCode(),
                    orderDeatails.getQty(),
                    orderDeatails.getDiscount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
