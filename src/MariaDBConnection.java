import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class MariaDBConnection {

    public static Connection getMariaDbConnection() {

        try {
            String MARIA_DB_CONNECTION_URL = "jdbc:mariadb://localhost:1903/library management system?user=root&password=root";
            return DriverManager.getConnection(MARIA_DB_CONNECTION_URL);
        } catch (SQLException e) {
            return null;
        }

    }


}
