package util;

import dbConnection.dbconnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Crudutil {

    public static <T>T execute(String sql, Object...args) throws SQLException {
        PreparedStatement preparedStatement = dbconnection.getInstance().getConnection().prepareStatement(sql);


        for (int i=0;i<args.length;i++){
            preparedStatement.setObject(i+1,args[i]);
        }

        if (sql.startsWith("SELECT")|| sql.startsWith("select")){
            return (T) preparedStatement.executeQuery();

        }else {
            return (T)(Boolean) (preparedStatement.executeUpdate()>0);
        }

    }
}
