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
    private Manager manager;

    private Department loginDepartment;

    private CleanerDAOImpl cleanerDAO = new CleanerDAOImpl();
    private OrganizerDAOImpl organizerDAO = new OrganizerDAOImpl();
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
                if (loginDepartment == Department.M){
                    logInForManager(tc);
                } else if (loginDepartment == Department.C){
                    logInForCleaner(tc);
                } else if (loginDepartment == Department.O){
                    logInForOrganizer(tc);
                }

            }else{
                //show error message
                JOptionPane.showMessageDialog(null, "Please enter your TC");
            }
        });

    }

    private void organizerButtonClicked() {
        organizerButton.addActionListener(event -> {
            showForLoggingIn("Organizer");
            loginDepartment = Department.O;
        });

    }

    private void cleanerButtonClicked(){
        cleanerButton.addActionListener(event->{
            showForLoggingIn("Cleaner");
            loginDepartment = Department.C;
        });


    }

    private void managerButtonClicked() {
        managerButton.addActionListener(event -> {
            showForLoggingIn("Manager");
            loginDepartment = Department.M;
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

    private void logInForManager(
            String tc
    ){

        try {
            ResultSet resultSet = managerDAO.getSpecificManager(tc);
            if (resultSet.next()) {
                //show managerPage
                manager = new Manager(
                        tc,
                        resultSet.getString("man_name"),
                        Department.valueOf(resultSet.getString("man_department")));
                ManagerPage.getInstance(manager).setVisible(true);
                this.setVisible(false);
                //System.out.printf("Entered for TC : %s\n", tc);
            }else {
                //show error message
                JOptionPane.showMessageDialog(null, "No such TC found");
            }
        }catch (Exception e){
            //show error message
            e.printStackTrace();
        }
    }


    private void logInForCleaner(
            String tc
    ){

        MistakeName mistakeName = cleanerDAO.logInForCleaner(tc);
        if (mistakeName == MistakeName.NO_ERROR){
            //show cleanerPage
            CleanerMainPage.getInstance(tc).setVisible(true);
            this.setVisible(false);
        }else if(mistakeName == MistakeName.NO_SUCH_TC_ERROR){
            //show error message
            JOptionPane.showMessageDialog(null, "No such TC found");
        }


    }

    private void logInForOrganizer(
            String tc
    ){

        MistakeName mistakeName = organizerDAO.logInForOrganizer(tc);
        if(mistakeName == MistakeName.NO_ERROR){
            //show organizerPage
            OrganizerMainPage.getInstance(tc).setVisible(true);
            this.setVisible(false);
        }else if (mistakeName == MistakeName.NO_SUCH_TC_ERROR) {
            //show error message
            JOptionPane.showMessageDialog(null, "No such TC found");
        }

    }

}
