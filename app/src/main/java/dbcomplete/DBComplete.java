package dbcomplete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBComplete {
    private static Connection conn;

    public static Connection getConn(){
        if (conn == null){
            try {
                conn = DriverManager.getConnection(LogCredentials.database, LogCredentials.name, LogCredentials.pass);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return conn;
    }
}
