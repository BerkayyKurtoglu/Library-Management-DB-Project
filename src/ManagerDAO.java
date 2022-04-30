import java.sql.ResultSet;

public interface ManagerDAO {

    ResultSet getSpecificManager(String managerTc);

    ResultSet getAllManager();


}
