import java.sql.ResultSet;

public interface OrganizerDAO {


    //Create a function to add new organizer
    public MistakeName addOrganizer(Organizer organizer);

    MistakeName logInForOrganizer(String tc);

    ResultSet getSpecificOrganizer(String tc);


}
