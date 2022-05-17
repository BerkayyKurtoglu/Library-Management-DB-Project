import javax.swing.*;

public class CleanerMainPage extends JFrame {

    //Make singleton
    private static CleanerMainPage instance = null;


    public static CleanerMainPage getInstance(
            String tc
    ) {

        if(instance == null){
            instance = new CleanerMainPage(tc);
        }
        return instance;

    }

    public CleanerMainPage(
            String tc
    ) {
        setTitle("Cleaner Main Page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
