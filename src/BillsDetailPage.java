import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class BillsDetailPage extends JFrame {

    private JPanel mainPanel;
    private JLabel billIDLabel;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private JTable table;
    private JButton closeButton;

    private static BillsDetailPage instance = null;
    private BillsDAOImpl billsDAO = new BillsDAOImpl();

    private String billId;

    public static BillsDetailPage getInstance(
            String billId
    ){
        if(instance == null){
            instance = new BillsDetailPage(
                    billId
            );
        }
        return instance;
    }

    public BillsDetailPage(
            String billId
    ) {

        this.billId = billId;
        setTitle("Bills Detail Page");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setContentPane(mainPanel);
        manageTable();
        clickedCloseButton();

    }

    private void manageTable(){

        try {
            ResultSet resultSet = billsDAO.getBillDetailsById(billId);
            ResultSet resultSet2 = billsDAO.getBillById(billId);
            refreshTable(resultSet,resultSet2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void refreshTable(
            ResultSet resultSetForDetails,
            ResultSet resultSetForBill
    ) throws SQLException {

        if (resultSetForDetails != null && resultSetForBill != null) {
            Vector<String> columnNames = new Vector<String>();
            ResultSetMetaData resultSetMetaData = resultSetForDetails.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(resultSetMetaData.getColumnName(i));
            }
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();

            while (resultSetForBill.next()){

                dateLabel.setText("Date : "+resultSetForBill.getString("date"));
                timeLabel.setText("Time : "+resultSetForBill.getString("time"));

            }
            while (resultSetForDetails.next()) {

                billIDLabel.setText("Bill ID : "+billId);

                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(resultSetForDetails.getObject(i));
                }
                data.add(vector);
                table.setModel(new DefaultTableModel(data, columnNames));

            }

        }

    }

    private void clickedCloseButton(){

        closeButton.addActionListener(e -> {
            try {
                BillsMainPage.getInstance().setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

    }



}
