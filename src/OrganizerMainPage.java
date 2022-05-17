import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizerMainPage extends JFrame {

    //Make singleton
    private static OrganizerMainPage instance = null;
    private JPanel mainPanel;
    private JButton booksAtSystemButton;
    private JButton booksThatWillBeButton;
    private JButton billsPageButton;
    private JButton addNewBookButton;
    private Organizer organizer;
    private OrganizerDAOImpl organizerDAO = new OrganizerDAOImpl();

    public static OrganizerMainPage getInstance(
            String tc
    ){

        if(instance == null){
            instance = new OrganizerMainPage(tc);
        }
        return instance;

    }
    public OrganizerMainPage(
            String tc
    ) {

        setTitle("Organizer Main Page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setContentPane(mainPanel);
        getOrganizer(tc);
        clickedBillsPageButton();
        clickedBooksAtSystemButton();
        clickedBooksThatWillBeButton();
        clickedAddNewBookButton();

    }

    private void clickedBooksAtSystemButton() {
        booksAtSystemButton.addActionListener(e -> {
            try {
                BooksAtSystemPage.getInstance().setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });
    }

    private void clickedBooksThatWillBeButton() {

    }

    private void clickedBillsPageButton() {
        billsPageButton.addActionListener(e -> {
            try {
                BillsMainPage.getInstance().setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

    }

    private void clickedAddNewBookButton() {

        addNewBookButton.addActionListener(e -> {
            BookAddingPage.getInstance(organizer).setVisible(true);
            dispose();
        });

    }

    private void getOrganizer(String tc) {

        ResultSet resultSet = organizerDAO.getSpecificOrganizer(tc);
        try {
            while (resultSet.next()) {
                organizer = new Organizer(
                        resultSet.getString("org_tc"),
                        resultSet.getString("org_name"),
                        resultSet.getFloat("org_maaş"),
                        resultSet.getString("man_tc")
                        );
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
