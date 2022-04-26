import javax.swing.*;

public class Driver {


    public static void main(String[] args) {

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(EntryPage::new);


    }


}