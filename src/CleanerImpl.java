import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CleanerImpl implements CleanerDAO {

    Statement statement;
    PreparedStatement preparedStatement;
    private final Connection connection  = MariaDBConnection.getMariaDbConnection();

    @Override
    public boolean addCleaner(Cleaner cleaner) {

        if (connection != null) {
            try {
                String cleanerName = cleaner.getCleanerName();
                String cleanerTc = cleaner.getCleanerTc();
                float cleanerWage = cleaner.getCleanerWage();
                String managerTc = cleaner.getManagerTc();
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO cleaner" +
                                    " (cle_tc, cle_name, cle_maaş, man_tc) " +
                                    "VALUES (?,?,?,?)"
                    );
                    preparedStatement.setString(1,cleanerTc);
                    preparedStatement.setString(2,cleanerName);
                    preparedStatement.setFloat(3,cleanerWage);
                    preparedStatement.setString(4,managerTc);
                    preparedStatement.executeUpdate();
                    return true;
            }catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            System.out.println("Connection is null");
            return false;
        }

    }


}
