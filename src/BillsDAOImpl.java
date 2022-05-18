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

    @Override
    public MistakeName addBillDetails(String billId, String bookIsbn, int pieces, String customerId) {

        if (connection != null) {
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT k_price FROM `kitap` where k_ısbn = '" + bookIsbn + "'");
                int totalPrice = 0;
                while (resultSet.next()){
                    totalPrice = resultSet.getInt("k_price") * pieces;
                }
                statement.executeUpdate("INSERT INTO `bill product` (`bill_id`, `book_id`,price) " +
                        "VALUES ('" + billId + "', '" + bookIsbn + "', '" + totalPrice + "')");
                return MistakeName.NO_ERROR;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return MistakeName.SQL_ERROR;
            }
        }else {
            return MistakeName.CONNECTION_ERROR;
        }

    }

    @Override
    public MistakeName addGeneralBill(String billId, String customerID) {

        if (connection != null) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO `general bill` (`bill_id`, `date`,time,customer_id) " +
                        "VALUES ('" + billId + "', CURRENT_TIMESTAMP, CURRENT_TIME,'" + customerID + "')");
                return MistakeName.NO_ERROR;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return MistakeName.SQL_ERROR;
            }
        }else {
            return MistakeName.CONNECTION_ERROR;
        }

    }

    @Override
    public MistakeName checkIfBookExists(String isbn) {

        if (connection != null) {
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT k_name FROM `kitap` WHERE `k_ısbn` = '" + isbn + "'");
                if (resultSet.next()){
                    return MistakeName.NO_ERROR;
                }else {
                    return MistakeName.NO_SUCH_BOOK_ERROR;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return MistakeName.SQL_ERROR;
            }
        }else {
            return MistakeName.CONNECTION_ERROR;
        }

    }


}
