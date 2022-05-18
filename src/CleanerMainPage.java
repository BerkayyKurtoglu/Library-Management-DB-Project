import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class CleanerMainPage extends JFrame {

    //Make singleton
    private static CleanerMainPage instance = null;
    private JComboBox<String> placeBox;
    private JButton saveButton;
    private JPanel mainPanel;
    private JButton seeCleanedPlacesButton;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton cancelButton;
    private CleanerDAOImpl cleanerDAO = new CleanerDAOImpl();


    public static CleanerMainPage getInstance(
            String tc
    ) throws SQLException {

        if(instance == null){
            instance = new CleanerMainPage(tc);
        }
        return instance;

    }

    public CleanerMainPage(
            String tc
    ) throws SQLException {

        setTitle("Cleaner Main Page");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setContentPane(mainPanel);
        scrollPane.setVisible(false);
        cancelButton.setVisible(false);

        //add items which are;
        // Restroom
        // Sitting Room 1
        // Sitting Room 2
        // Cofee Shop
        // and set default value to Sitting Room 1
        placeBox.addItem("Restroom");
        placeBox.addItem("Sitting Room 1");
        placeBox.addItem("Sitting Room 2");
        placeBox.addItem("Coffee Shop");
        placeBox.setSelectedIndex(1);
        table.setDefaultEditor(Object.class, null);
        clickedSaveButton();
        clickedSeeCleanedPlacesButton();
        clickedCancelButton();

    }

    private void clickedSaveButton(){

        saveButton.addActionListener(e->{

            String place = (String) placeBox.getSelectedItem();
            assert place != null;
            if(place.equals("")){
                JOptionPane.showMessageDialog(null, "Please select a place");
            }
            else{

                MistakeName mistakeName = cleanerDAO.addReport(new Report(place));
                if (mistakeName == MistakeName.NO_ERROR){
                    JOptionPane.showMessageDialog(null, "Report added successfully");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Report added failed");
                }

            }

        });

    }

    private void clickedSeeCleanedPlacesButton() throws SQLException{

        seeCleanedPlacesButton.addActionListener(e->{

            scrollPane.setVisible(true);
            placeBox.setVisible(false);
            saveButton.setVisible(false);
            seeCleanedPlacesButton.setVisible(false);
            cancelButton.setVisible(true);

            CleanerDAOImpl cleanerDAO = new CleanerDAOImpl();
            ResultSet resultSet = cleanerDAO.getCleanedPlaces();
            if (resultSet!=null){

                try {
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
                    table.setModel(new DefaultTableModel(data, columnNames));
                }catch (Exception ex){
                    ex.printStackTrace();
                }


            }
            else{
                JOptionPane.showMessageDialog(null, "No data found");
            }

        });

    }

    private void clickedCancelButton(){

    	cancelButton.addActionListener(e->{

    		scrollPane.setVisible(false);
    		placeBox.setVisible(true);
            cancelButton.setVisible(false);
            saveButton.setVisible(true);
            seeCleanedPlacesButton.setVisible(true);

    	});

    }


}
