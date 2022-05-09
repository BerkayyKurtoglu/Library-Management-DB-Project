import javax.swing.*;

public class AddingPage extends JFrame {
    private JPanel mainPanel;
    private final Department department;
    private static AddingPage addingPage = null;

    public static AddingPage getInstance(
            Department department
    ) {
        if (addingPage == null) {
            addingPage = new AddingPage(department);
        }
        return addingPage;
    }

    public AddingPage(
            Department department
    ) {
        this.department = department;
        manageEntryLabels();
        initComponents();
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
        }else{
            this.setTitle("Organizer Adding Page");
        }
    }


}
