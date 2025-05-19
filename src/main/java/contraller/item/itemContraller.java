package contraller.item;

import dbConnection.dbconnection;
import model.item;
import util.Crudutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class itemContraller implements itemService{
    @Override
    public boolean addItem(item itm) {
        String sql ="insert into items values(?,?,?,?,?,?,?,?)";
boolean b;
        try {


            Crudutil.execute(sql,itm.getId(),
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
        return false;
    }

    @Override
    public boolean updateItem(item itm) {
        return false;
    }

    @Override
    public item searchCoustomer(String id) {
        return null;
    }
}
