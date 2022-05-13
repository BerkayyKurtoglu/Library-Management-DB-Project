import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class OrganizerDAOImpl implements OrganizerDAO {


    Statement statement;
    PreparedStatement preparedStatement;
    private final Connection connection  = MariaDBConnection.getMariaDbConnection();

    @Override
    public boolean addOrganizer(Organizer organizer) {

        if (connection != null) {
            try {
                String organizerName = organizer.getOrganizerName();
                String organizerTc = organizer.getOrganizerId();
                float organizerSalary = organizer.getOrganizerSalary();
                String managerTc = organizer.getManagerId();

                if (organizerName.length() > 0 && organizerTc.length() > 0 && organizerSalary > 0 && managerTc.length() > 0) {

                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO orginizer" +
                                    " (org_tc, org_name, org_maaş,man_tc) " +
                                    "VALUES (?,?,?,?)"
                    );
                    preparedStatement.setString(1,organizerTc);
                    preparedStatement.setString(2,organizerName);
                    preparedStatement.setFloat(3,organizerSalary);
                    preparedStatement.setString(4,managerTc);
                    preparedStatement.executeUpdate();
                    return true;

                }else return false;

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
