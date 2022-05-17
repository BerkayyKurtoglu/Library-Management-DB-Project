import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class Driver {

    public static void main(String[] args) {


        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(EntryPage::new);
        //Get current date with time and convert it to a java.sql.Time object
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Time time = new Time(Calendar.getInstance().getTime().getTime());
        System.out.println(
                date.toString() + " " + time.toString()
        );

    }


}
