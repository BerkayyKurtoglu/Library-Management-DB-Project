import javax.swing.*;

public class MakeASalePage extends JFrame {

    private static MakeASalePage instance = null;
    private JTable table;
    private JTextField ısbn_fiield;
    private JButton continuoToAddButton;
    private JButton finishButton;
    private JPanel mainPanel;


    public static MakeASalePage getInstance(){
        if(instance == null){
            instance = new MakeASalePage();
        }
        return instance;
    }

    public MakeASalePage(){

        setTitle("Make a Sale");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setContentPane(mainPanel);


    }

    private void clickedContiouAddButton(){

        refreshTable();

    }

    private void refreshTable(){



    }


}
