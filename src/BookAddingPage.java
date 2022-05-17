import javax.swing.*;

public class BookAddingPage extends JFrame {
    private static BookAddingPage instance = null;
    private JTextField ısbnField;
    private JTextField authorField;
    private JTextField priceField;
    private JTextField stockFİeld;
    private JButton addToSystemButton;
    private JPanel mainPanel;
    private JTextField nameField;
    private BooksDAOImpl booksDAO = new BooksDAOImpl();
    private Organizer organizer;

    public static BookAddingPage getInstance(
            Organizer organizer
    ) {
        if (instance == null) {
            instance = new BookAddingPage();
        }
        return instance;
    }

    public BookAddingPage() {

        setTitle("Book Adding Page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(mainPanel);
        clickedAddToSystemButton();

    }


    private void clickedAddToSystemButton(){

        addToSystemButton.addActionListener(es->{

            String isbn = ısbnField.getText();
            String name = nameField.getText();
            String author = authorField.getText();
            String price = priceField.getText();
            String stock = stockFİeld.getText();
            if (price == null || price.equals("") || stock == null || stock.equals("")){
                JOptionPane.showMessageDialog(null, "Please fill all fields");
            }else{
                MistakeName result = booksDAO.addBook(
                        isbn,
                        name,
                        author,
                        Float.parseFloat(price),
                        Integer.parseInt(stock)
                );
                if (result == MistakeName.NO_ERROR){
                    JOptionPane.showMessageDialog(null, "Book added successfully");
                    OrganizerMainPage.getInstance(organizer.getOrganizerId()).setVisible(true);
                    dispose();
                }else if (result == MistakeName.EMPTY_FIELD_ERROR){
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                }
            }
        });

    }


}



