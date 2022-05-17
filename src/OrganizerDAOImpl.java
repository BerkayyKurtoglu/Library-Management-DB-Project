import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrganizerDAOImpl implements OrganizerDAO {


    Statement statement;
    PreparedStatement preparedStatement;
    private final Connection connection  = MariaDBConnection.getMariaDbConnection();
    private String organizerTc = "";
    private String organizerName = "";
    private float organizerSalary = 0;
    private String managerTc = "";

    @Override
    public MistakeName addOrganizer(Organizer organizer) {

        organizerName = organizer.getOrganizerName();
        organizerTc = organizer.getOrganizerId();
        organizerSalary = organizer.getOrganizerSalary();
        managerTc = organizer.getManagerId();

        if (connection != null) {
            MistakeName error = checkIfFieldsIsNotEmtyp();
            if (error == MistakeName.NO_ERROR){
                try {
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
                        return MistakeName.NO_ERROR;

                }catch (Exception e) {
                    e.printStackTrace();
                    return MistakeName.SQL_ERROR;
                }
            } else if (error == MistakeName.EMPTY_FIELD_ERROR) {
                return MistakeName.EMPTY_FIELD_ERROR;
            }else if (error == MistakeName.TC_TYPE_ERROR){
                return MistakeName.TC_TYPE_ERROR;
            }else {
                return MistakeName.GENERIC_ERROR;
            }

        }else{
            System.out.println("Connection is null");
            return MistakeName.CONNECTION_ERROR;
        }
    }

    @Override
    public MistakeName logInForOrganizer(String tc) {

        if (connection != null){

            try{

                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT * FROM orginizer WHERE org_tc = '" + tc + "'"
                );
                if (resultSet.next()){
                    return MistakeName.NO_ERROR;
                }else{
                    return MistakeName.NO_SUCH_TC_ERROR;
                }
            }catch (Exception e){
                e.printStackTrace();
                return MistakeName.SQL_ERROR;
            }

        }else {
            System.out.println("Connection is null");
            return MistakeName.CONNECTION_ERROR;
        }

    }

    @Override
    public ResultSet getSpecificOrganizer(String tc) {

        if (connection != null){
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT * FROM orginizer WHERE org_tc = '" + tc + "'"
                );
                return resultSet;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }else {
            System.out.println("Connection is null");
            return null;
        }

    }

    private MistakeName checkIfFieldsIsNotEmtyp(){

        if(organizerTc.isEmpty() || organizerName.isEmpty() || managerTc.isEmpty() || organizerSalary == 0){
            return MistakeName.EMPTY_FIELD_ERROR;
        }else if(organizerTc.length() != 11){
            return MistakeName.TC_TYPE_ERROR;
        }else {
            return MistakeName.NO_ERROR;
        }

    }



}
