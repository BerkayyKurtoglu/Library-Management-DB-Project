import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CleanerDAOImpl implements CleanerDAO {

    Statement statement;
    PreparedStatement preparedStatement;
    private final Connection connection  = MariaDBConnection.getMariaDbConnection();
    private String cleanerTc = "";
    private String cleanerName = "";
    private float cleanerWage = 0;
    private String managerTc = "";

    @Override
    public MistakeName addCleaner(Cleaner cleaner) {

        cleanerName = cleaner.getCleanerName();
        cleanerTc = cleaner.getCleanerTc();
        cleanerWage = cleaner.getCleanerWage();
        managerTc = cleaner.getManagerTc();
        MistakeName error = checkIfFieldsIsNotEmtyp();

        if (connection != null) {
            if (error == MistakeName.NO_ERROR) {
                try {

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
                    return MistakeName.NO_ERROR;
                }catch (Exception e) {
                    e.printStackTrace();
                    return MistakeName.SQL_ERROR;
                }
            }else if (error == MistakeName.EMPTY_FIELD_ERROR) {
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
    public MistakeName logInForCleaner(String tc) {

        if (connection != null){
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT * FROM cleaner WHERE cle_tc = '" + tc + "'"
                );
                if (resultSet.next()){
                    return MistakeName.NO_ERROR;
                }else{
                    return MistakeName.NO_SUCH_TC_ERROR;
                }
            }catch (Exception e){
                return MistakeName.SQL_ERROR;
            }
        }else {
            return MistakeName.CONNECTION_ERROR;
        }
    }

    @Override
    public MistakeName addReport(Report report) {

        if (connection != null){

            try {

                statement = connection.createStatement();
                statement.executeQuery("INSERT INTO `cleaner report`" +
                        "(space,date,time) VALUES" +
                        " ('" + report.getPlace() + "', CURRENT_TIMESTAMP, CURRENT_TIME)");

                return MistakeName.NO_ERROR;

            }catch (Exception e){
                e.printStackTrace();
                return MistakeName.SQL_ERROR;
            }

        }
        else {
            return MistakeName.CONNECTION_ERROR;
        }


    }

    @Override
    public ResultSet getCleanedPlaces() {


        if (connection != null) {
            try {
                statement = connection.createStatement();
                return statement.executeQuery(
                        "SELECT * FROM `cleaner report`"
                );
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
        else {
            return null;
        }

    }
    private MistakeName checkIfFieldsIsNotEmtyp(){

        if(cleanerTc.isEmpty() || cleanerName.isEmpty() || managerTc.isEmpty() || cleanerWage == 0){
            return MistakeName.EMPTY_FIELD_ERROR;
        }else if(cleanerTc.length() != 11){
            return MistakeName.TC_TYPE_ERROR;
        }else {
            return MistakeName.NO_ERROR;
        }

    }





}
