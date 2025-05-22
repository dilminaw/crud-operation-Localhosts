package contraller.item;

import dbConnection.dbconnection;
import model.item;
import util.Crudutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class itemContraller implements itemService {
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
        try {
            Connection connection = dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM items WHERE ItemID='" + id + "'");

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public boolean updateItem(item itm) {
        String Sql = "UPDATE items set ItemName=?,Category=?,Price=?,Quantity=?,Supplier=?,WarehouseLocation=?,AddedDate=? WHERE ItemID=?";
        try {
            Connection connection = dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Sql);

            preparedStatement.setObject(1, itm.getName());
            preparedStatement.setObject(2, itm.getCategory());
            preparedStatement.setObject(3, itm.getPrice());
            preparedStatement.setObject(4, itm.getQuantity());
            preparedStatement.setObject(5, itm.getSuplier());
            preparedStatement.setObject(6, itm.getLocation());
            preparedStatement.setObject(7, itm.getDate());
            preparedStatement.setObject(8, itm.getId());
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public item searchCoustomer(String id) {
        String sql = "SELECT * FROM items WHERE ItemID='" + id + "'";

        try {
            Connection connection = dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                return new item(resultSet.getString("ItemID"),
                        resultSet.getString("ItemName"),
                        resultSet.getString("Category"),
                        resultSet.getDouble("Price"),
                        resultSet.getInt("Quantity"),
                        resultSet.getString("Supplier"),
                        resultSet.getString("WarehouseLocation"),
                        resultSet.getDate("AddedDate").toLocalDate());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return null;
    }
}


