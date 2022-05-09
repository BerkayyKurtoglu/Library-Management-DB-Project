import javax.swing.*;

public class ManagerPage extends JFrame {

    //Create singleton instance
    private static ManagerPage instance = null;
    private JPanel mainPanel;
    private JLabel welcomeLabel;
    private JButton addNewEmployee;
    private final Manager manager;

    public static ManagerPage getInstance(
            Manager manager
    ) {
        if (instance == null) {
            instance = new ManagerPage(manager);
        }
        return instance;
    }

    public ManagerPage(
            Manager manager
    ) {
        this.manager = manager;
        initComponents();
    }
    private void initComponents() {
        setVisible(true);
        mainPanel.setVisible(true);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        manageEntryLabels();
        addNewEmployeeClicked();
    }

    public void manageEntryLabels(){

        if (manager.getManagerDepartment() == Department.C) {
            this.setTitle("Manager For Cleaning Department");
            addNewEmployee.setText("Add New Cleaner");
            welcomeLabel.setText("Welcome "+manager.getManagerName());
        }else{
            this.setTitle("Manager For Organizing Department");
            addNewEmployee.setText("Add New Organizer");
            welcomeLabel.setText("Welcome "+manager.getManagerName());
        }

    }

    private void addNewEmployeeClicked() {

        addNewEmployee.addActionListener(event->{

            if (manager.getManagerDepartment() == Department.C) {
                //show cleanerAddingPage
                AddingPage.getInstance(Department.C,manager).setVisible(true);
            }else{
                //show organizerAddingPage
                AddingPage.getInstance(Department.O,manager).setVisible(true);
            }

        });

    }

}
