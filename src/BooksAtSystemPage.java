import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class BooksAtSystemPage extends JFrame {

    //Make singleton
    private static BooksAtSystemPage instance = null;
    private JPanel mainPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JTable booksTable;
    private JButton cleanSearchingButton;
    private final String[] columns = {"Book ID", "Book Name", "Author", "Price", "Pieces"};
    private String [][] books = new String[0][5];
    private BooksDAOImpl booksDAO = new BooksDAOImpl();
    private DefaultTableModel tableModel = new DefaultTableModel(books, columns);

    public static BooksAtSystemPage getInstance() throws SQLException {

        if(instance == null){
            instance = new BooksAtSystemPage();
        }
        return instance;

    }

    public BooksAtSystemPage() throws SQLException {

        setTitle("Books At System Page");
        cleanSearchingButton.setVisible(false);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(mainPanel);
        manageTable();
        makeSearch();
        clickedCleanSearchingButton();

    }

    private void manageTable() throws SQLException {

        booksTable.setShowGrid(true);
        booksTable.setShowVerticalLines(true);
        booksTable.setDefaultEditor(Object.class, null);

        ResultSet resultSet = booksDAO.getAllBooks();
        refreshTable(resultSet);
    }

    private void makeSearch(){

        searchButton.addActionListener(es->{

            String searchText = searchTextField.getText();
            if (searchText.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter a search text");
            }else{
                try {
                    ResultSet resultSet = booksDAO.getBookByName(searchText);
                    refreshTable(resultSet);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                cleanSearchingButton.setVisible(true);
            }

        });


    }

    private void refreshTable(
            ResultSet resultSet
    ) throws SQLException {
        if (resultSet != null) {
            Vector<String> columnNames = new Vector<String>();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(resultSetMetaData.getColumnName(i));
            }
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (resultSet.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(resultSet.getObject(i));
                }
                data.add(vector);
            }
            booksTable.setModel(new DefaultTableModel(data, columnNames));
        }else{
            System.out.println("ResultSet is null");
            JOptionPane.showMessageDialog(null, "Something went wrong");
        }

    }

    private void clickedCleanSearchingButton(){

        cleanSearchingButton.addActionListener(es->{

            try {
                searchTextField.setText("");
                ResultSet resultSet = booksDAO.getAllBooks();
                refreshTable(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            cleanSearchingButton.setVisible(false);

        });

    }


}
