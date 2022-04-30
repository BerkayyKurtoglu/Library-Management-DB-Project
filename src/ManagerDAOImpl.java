import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerDAOImpl implements ManagerDAO {

    private final Connection connection = MariaDBConnection.getMariaDbConnection();
    private Statement statement;

    @Override
    public ResultSet getSpecificManager(String managerTc) {

        try {
            assert connection != null;
            statement = connection.createStatement();
            return statement.executeQuery(
                    "SELECT * FROM `library management system`.manager WHERE man_tc = '" + managerTc + "'"
            );

        }catch (Exception e){
            return null;
        }

    }

    @Override
    public ResultSet getAllManager() {
        return null;
    }


}
