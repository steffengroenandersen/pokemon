package dk.kea.pokemon.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DATABASE CONNECTION SINGLETON
public class ConnectionManager {
    private static Connection connection = null;

    // CONNECT TO DATABASE
    public static Connection getConnection(String db_url, String uid, String pwd){
        // Check if connection is initialized
        if(connection != null) return connection;

        // Initialize connection
        try{
            connection = DriverManager.getConnection(db_url, uid, pwd);
        } catch(SQLException e){
            System.out.println("Could not connect to database");
            e.printStackTrace();
        }
        return connection;
    }
}
