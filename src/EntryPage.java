import javax.swing.*;
import java.sql.ResultSet;

public class EntryPage extends JFrame {
    private JPanel mainPanel;
    private JButton cleanerButton;
    private JButton organizerButton;
    private JButton managerButton;
    private JButton logInButton;
    private JTextField tcTextField;
    private JLabel entryLabel;
    private JLabel tcLabel;
    private JButton cancelButton;

    ManagerDAOImpl managerDAO = new ManagerDAOImpl();

    public EntryPage() {

        super("Library Management System");
        setVisible(true);
        mainPanel.setVisible(true);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);

        tcTextField.setVisible(false);
        logInButton.setVisible(false);
        tcLabel.setVisible(false);
        cancelButton.setVisible(false);

        managerButtonClicked();
        organizerButtonClicked();
        cleanerButtonClicked();
        cancelButtonClickec();
        logInButtonListener();
        listenTcTextField();

    }

    private String listenTcTextField(){

        if (tcTextField.getText().length() == 0) {
            return null;
        }else {
            return tcTextField.getText();
        }

    }

    private void logInButtonListener(){
        logInButton.addActionListener(event->{
            String tc = listenTcTextField();
            if (tc != null) {
                try {
                    ResultSet resultSet = managerDAO.getSpecificManager(tc);
                    if (resultSet.next()) {
                        System.out.printf("Entered for TC : %s\n", tc);
                    }else {
                        JOptionPane.showMessageDialog(null, "No such TC found");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Please enter your TC");
            }
        });

    }

    private void organizerButtonClicked() {
        organizerButton.addActionListener(event -> {



        });

    }

    private void cleanerButtonClicked(){
        cleanerButton.addActionListener(event->{



        });


    }

    private void managerButtonClicked() {
        managerButton.addActionListener(event -> {
            showForLoggingIn("Manager");
        });
    }

    private void cancelButtonClickec(){
        cancelButton.addActionListener(event->{

            hideForLoggingIn();

        });
    }

    private void showForLoggingIn(String fromWhere){

        //hide managerButton,cleanerButton,organizerButton then show tcTextField and logInButton

        entryLabel.setText("Log In For : "+fromWhere);
        cancelButton.setVisible(true);
        tcLabel.setVisible(true);
        managerButton.setVisible(false);
        cleanerButton.setVisible(false);
        organizerButton.setVisible(false);
        tcTextField.setVisible(true);
        logInButton.setVisible(true);

    }

    private void hideForLoggingIn(){

        //hide tcTextField and logInButton then show managerButton,cleanerButton,organizerButton
        tcLabel.setVisible(false);
        entryLabel.setText("LOG IN AS; ");
        cancelButton.setVisible(false);
        tcTextField.setVisible(false);
        logInButton.setVisible(false);
        managerButton.setVisible(true);
        cleanerButton.setVisible(true);
        organizerButton.setVisible(true);

    }

}
