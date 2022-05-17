import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class BillsMainPage extends JFrame {


    private static BillsMainPage instance = null;
    private JPanel mainPanel;
    private JTable billsTable;
    private JButton makeASaleButton;
    private JButton cancelButton;
    private BillsDAOImpl billsDAO = new BillsDAOImpl();
    private ArrayList<String> billsIdList = new ArrayList<>();


    public static BillsMainPage getInstance() throws SQLException {

        if(instance == null){
            instance = new BillsMainPage();
        }
        return instance;

    }

    public BillsMainPage() throws SQLException {

        setTitle("Bills Main Page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(mainPanel);
        manageBillsTable();
        tableListener();
        clickedMakeASaleButton();

    }


    private void manageBillsTable() throws SQLException {

        billsTable.setShowGrid(true);
        billsTable.setShowVerticalLines(true);
        billsTable.setDefaultEditor(Object.class, null);

        refreshBillsTable(billsDAO.getAllBills());

    }

    private void clickedMakeASaleButton(){

        makeASaleButton.addActionListener(e -> {
            MakeASalePage.getInstance().setVisible(true);
            dispose();
        });

    }

    private void refreshBillsTable(
            ResultSet resultSet
    ) throws SQLException {
        if (resultSet != null) {
            billsIdList.clear();
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
                billsIdList.add(resultSet.getString("bill_id"));
            }
        billsTable.setModel(new DefaultTableModel(data, columnNames));
        }else {
            billsTable.setModel(new DefaultTableModel());
        }

    }

    private void tableListener(){

        billsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    int row = billsTable.getSelectedRow();
                    String billId = billsIdList.get(row);
                    BillsDetailPage.getInstance(billId).setVisible(true);
                }
                super.mouseClicked(e);
            }
        });

    }

}
