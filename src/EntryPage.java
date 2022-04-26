import javax.swing.*;

public class EntryPage extends JFrame {
    private JButton organizorButton;
    private JButton cleanerButton;
    private JPanel mainPanel;

    public EntryPage() {

        super("Library Management System");
        setVisible(true);
        mainPanel.setVisible(true);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
    }

    private void manageOrganizerButton() {

        organizorButton.setFocusable(false);

    }


    private void manageCleanerButton() {

        cleanerButton.setFocusable(false);

    }


}
