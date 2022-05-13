import javax.swing.*;

public class AddingPage extends JFrame {
    private JPanel mainPanel;
    private JTextField nameTextField;
    private JTextField idTextField;
    private JTextField salaryTextField;
    private JButton saveButton;
    private JLabel entryLabel;
    private final Department department;
    private static AddingPage addingPage = null;
    private final Manager manager;
    private String employeeId;
    private String employeeName;
    private float employeeSalary;
    private final CleanerDAOImpl cleanerImpl = new CleanerDAOImpl();
    private final OrganizerDAOImpl organizerImpl = new OrganizerDAOImpl();

    public static AddingPage getInstance(
            Department department,
            Manager manager
    ) {
        if (addingPage == null) {
            addingPage = new AddingPage(department, manager);
        }
        return addingPage;
    }

    public AddingPage(
            Department department,
            Manager manager
    ){
        this.department = department;
        this.manager = manager;
        manageEntryLabels();
        initComponents();

        saveButtonClicked();
    }

    private void initComponents() {
        setVisible(true);
        mainPanel.setVisible(true);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
    }

    private void manageEntryLabels(){
        if (department == Department.C) {
            this.setTitle("Cleaner Adding Page");
            entryLabel.setText("Adding New Cleaner...");
        }else{
            this.setTitle("Organizer Adding Page");
            entryLabel.setText("Adding New Organizer...");
        }
    }

    //Create function to save new employee named saveButtonClicked
    private void saveButtonClicked() {

        saveButton.addActionListener(event-> {
            employeeId = idTextField.getText();
            employeeName = nameTextField.getText();
            employeeSalary = Float.parseFloat(salaryTextField.getText());

            if (department == Department.C) {
                //Create new cleaner
                Cleaner cleaner = new Cleaner(
                        employeeId,
                        employeeName,
                        employeeSalary,
                        manager.getManagerTc()
                );
                //Add new cleaner to cleanerDAO
                if(cleanerImpl.addCleaner(cleaner)){
                    JOptionPane.showMessageDialog(null, "New Cleaner Added Successfully!");
                }else {
                    JOptionPane.showMessageDialog(null, "Failed to add new cleaner!");
                }
                setVisible(false);
                ManagerPage.getInstance(manager).setVisible(true);
            }else {
                //Create new organizer
                Organizer organizer = new Organizer(
                        employeeId,
                        employeeName,
                        employeeSalary,
                        manager.getManagerTc()
                );
                //Add new organizer to organizerDAO
                if(organizerImpl.addOrganizer(organizer)){
                    JOptionPane.showMessageDialog(null, "New Organizer Added Successfully!");
                }else {
                    JOptionPane.showMessageDialog(null, "Failed to add new organizer!");
                }
                setVisible(false);
                ManagerPage.getInstance(manager).setVisible(true);

            }

        });
    }


}
