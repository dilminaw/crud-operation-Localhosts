package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {
 private static    dbconnection instance;

    private Connection connection;

    private dbconnection() throws SQLException {
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","1234");
    }

    public static dbconnection getInstance() throws SQLException {
        return null==instance?new dbconnection():instance;
    }
    public Connection getConnection(){
        return connection;
    }
}

