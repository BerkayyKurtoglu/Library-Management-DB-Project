import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class MakeASalePage extends JFrame {

    private static MakeASalePage instance = null;
    private JTable table;
    private JTextField ısbn_fiield;
    private JButton continuoToAddButton;
    private JButton finishButton;
    private JPanel mainPanel;
    private JTextField pieces_field;
    private JTextField id_field;
    private JLabel bill_id_label;
    private JTextField customerIdField;
    private JLabel customerIdLabel;
    private String[] header = {"ISBN", "PIECES"};
    private DefaultTableModel defaultTableModel = new DefaultTableModel(header, 0);
    private String isbn = "";
    private int pieces = 0;
    private String billId = "";
    private String customerId = "";
    boolean isFirst = true;
    private ArrayList<String> isbnList = new ArrayList<>();
    private ArrayList<Integer> piecesList = new ArrayList<>();
    private ArrayList<String> billIdList = new ArrayList<>();
    private BillsDAOImpl billsDAO = new BillsDAOImpl();


    public static MakeASalePage getInstance(){
        if(instance == null){
            instance = new MakeASalePage();
        }
        return instance;
    }

    public MakeASalePage(){

        finishButton.setVisible(false);
        setTitle("Make a Sale");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(mainPanel);

        clickedFinishButton();
        clickedContinueToAddButton();

    }

    private void clickedContinueToAddButton(){
        continuoToAddButton.addActionListener(e -> {
            MistakeName mistakeName = checkFields();
            if (mistakeName == MistakeName.NO_ERROR){
                if (isFirst){
                    id_field.setVisible(false);
                    bill_id_label.setVisible(false);
                    customerIdField.setVisible(false);
                    customerIdLabel.setVisible(false);
                    isFirst = false;
                }
                isbn = ısbn_fiield.getText();
                pieces = Integer.parseInt(pieces_field.getText());
                billId = id_field.getText();
                System.out.println(billId);
                if (billsDAO.checkIfBookExists(isbn) == MistakeName.NO_SUCH_BOOK_ERROR){
                    JOptionPane.showMessageDialog(null, "There is no such book");
                }
                else {
                    refreshTable(isbn, pieces);
                    ısbn_fiield.setText("");
                    pieces_field.setText("");
                    if (!isbnList.isEmpty()){
                        finishButton.setVisible(true);
                    }
                }
            }
            else if(mistakeName == MistakeName.PARSING_ERROR){
                JOptionPane.showMessageDialog(null,"Please enter a number in the pieces field");
            }else{
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            }
        });

    }

    private void refreshTable(
            String isbn,
            int pieces
    ){
        defaultTableModel.addRow(new Object[]{isbn, pieces});
        table.setModel(defaultTableModel);
        isbnList.add(isbn);
        piecesList.add(pieces);
        billIdList.add(billId);
    }

    private void clickedFinishButton(){

        finishButton.addActionListener(e -> {
            if (isbnList.size() == 0){
                JOptionPane.showMessageDialog(null, "Please add at least one book");
            }else{
                MistakeName mistakeName = billsDAO.addGeneralBill(billId,customerId );
                if (mistakeName == MistakeName.NO_ERROR){
                    for (int i = 0; i < isbnList.size(); i++){
                        MistakeName error = billsDAO.addBillDetails(billId, isbnList.get(i), piecesList.get(i), customerId);
                        if(error != MistakeName.NO_ERROR){
                            JOptionPane.showMessageDialog(null, "Error in adding bill");
                            break;
                        }
                        try {
                            BillsMainPage.setInstance(null);
                            BillsMainPage.getInstance().setVisible(true);
                            dispose();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error in adding bill");
                }
            }
        });

    }

    private MistakeName checkFields(){
        try {
            isbn = ısbn_fiield.getText();
            billId = id_field.getText();
            customerId = customerIdField.getText();
            pieces = Integer.parseInt(pieces_field.getText());
            if (isbn.equals("") || billId.equals("") || customerId.equals("") || pieces == 0){
                return MistakeName.EMPTY_FIELD_ERROR;
            }else{
                return MistakeName.NO_ERROR;
            }

        }catch (Exception e){
            return MistakeName.PARSING_ERROR;
        }
    }
}
