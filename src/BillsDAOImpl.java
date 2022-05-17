import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BillsDAOImpl implements BillsDAO{

    Connection connection = MariaDBConnection.getMariaDbConnection();
    Statement statement;


    @Override
    public ResultSet getAllBills() {

        if (connection != null) {
            try {
                statement = connection.createStatement();
                return statement.executeQuery("SELECT * FROM `general bill`");
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        }else {
            return null;
        }

    }

    @Override
    public ResultSet getBillDetailsById(String billId) {

        if (connection != null) {
            try {
                statement = connection.createStatement();
                return statement.executeQuery("SELECT * FROM `bill product` WHERE `bill_id` = '" + billId + "'");
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        }else {
        return null;
        }

    }

    @Override
    public ResultSet getBillById(String billId) {

        if (connection != null) {
            try {
                statement = connection.createStatement();
                return statement.executeQuery("SELECT * FROM `general bill` WHERE `bill_id` = '" + billId + "'");
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        }else {
            return null;
        }


    }


}
