import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        organizorButton.addActionListener(actionEvent -> {
            //TODO: implement action of manage organizer button
        });

    }


    private void manageCleanerButton() {

        cleanerButton.setFocusable(false);
        cleanerButton.addActionListener(actionEvent -> {
            //TODO: implement action of cleanerButton
        });


    }


}
