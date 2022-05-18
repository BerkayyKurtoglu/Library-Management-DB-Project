import java.sql.ResultSet;

public interface CleanerDAO {

    MistakeName addCleaner(Cleaner cleaner);

    MistakeName logInForCleaner(String tc);

    MistakeName addReport(Report report);

    ResultSet getCleanedPlaces();

}
