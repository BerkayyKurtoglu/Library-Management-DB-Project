import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BooksDAOImpl implements BooksDAO{

    private Connection connection = MariaDBConnection.getMariaDbConnection();
    private Statement statement;
    private ResultSet resultSet;

    @Override
    public ResultSet getAllBooks() {

        if (connection != null) {
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM kitap");
                return resultSet;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            System.out.println("Connection is null");
            return null;
        }

    }

    @Override
    public ResultSet getBookByName(String bookName) {

        if (connection != null) {
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(
                        "SELECT * FROM kitap WHERE k_name = '" + bookName + "'"
                );
                return resultSet;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        }else {
        System.out.println("Connection is null");
        return null;
        }

    }

    @Override
    public MistakeName addBook(String bookIsbn, String bookName, String bookAuthor, float bookPrice, int bookStock) {

        if (connection != null){
            MistakeName error = checkFields(bookIsbn, bookName, bookAuthor, bookPrice, bookStock);
            if (error == MistakeName.NO_ERROR){
                try {
                    statement = connection.createStatement();
                    statement.executeUpdate(
                            "INSERT INTO kitap " +
                                    "(k_ısbn, k_name, k_author, k_price,k_piece) " +
                                    "VALUES ('" + bookIsbn + "', '" + bookName + "', '" + bookAuthor + "', " + bookPrice + ", " + bookStock + ")"
                    );
                    return MistakeName.NO_ERROR;
                }catch (SQLException e){
                    e.printStackTrace();
                    return MistakeName.SQL_ERROR;
                }

            }else{
                return error;
            }

        }else {
            System.out.println("Connection is null");
            return MistakeName.CONNECTION_ERROR;
        }


    }

    private MistakeName checkFields(
            String bookIsbn,
            String bookName,
            String bookAuthor,
            float bookPrice,
            int bookStock
            ){

        if (bookIsbn.equals("") || bookName.equals("") || bookAuthor.equals("") || bookPrice == 0 || bookStock == 0){
            return MistakeName.EMPTY_FIELD_ERROR;
        }else {
            return MistakeName.NO_ERROR;
        }

    }

}
