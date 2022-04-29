import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver {

    public static void main(String[] args) throws SQLException {

        Connection connection = MariaDBConnection.getMariaDbConnection();

        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `library management system`.manager");
            while (resultSet.next()) {
                System.out.println(
                                resultSet.getString("man_name")+" "
                                +resultSet.getString("man_tc")+" "
                                +resultSet.getString("man_department")
                );
            }
        }else {
            System.out.println("Connection failed");
        }

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(EntryPage::new);


    }


}
