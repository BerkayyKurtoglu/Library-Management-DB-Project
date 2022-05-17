import java.sql.ResultSet;

public interface BooksDAO {

    ResultSet getAllBooks();

    ResultSet getBookByName(String bookName);

    MistakeName addBook(
            String bookIsbn,
            String bookName,
            String bookAuthor,
            float bookPrice,
            int bookStock
            );


}
