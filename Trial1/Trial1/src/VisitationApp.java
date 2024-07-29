import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VisitationApp extends JFrame {
    private JLabel visitorNameLabel;
    JLabel heading;
    JButton backButton;
    private JTextField visitorNameField;
    private JLabel criminalNameLabel;
    private JTextField criminalNameField;
    private JLabel visitDateLabel;
    private JTextField visitDateField;
    private JLabel relationLabel;
    private JTextField relationField;
    private JLabel contactNumberLabel;
    private JTextField contactNumberField;
    private JLabel locationLabel;
    private JTextField locationField;
    private JButton addButton;

    public VisitationApp() {
        setTitle("Visitation Records");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(new Color(230, 230, 250));
          heading = new JLabel("ADDING VISITORS INFO");
        heading.setBounds(390, -10, 800, 200);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel shadow = new JLabel("ADDING VISITORS INFO");
        shadow.setBounds(heading.getX() + 4, heading.getY() + 4, heading.getWidth(), heading.getHeight());
        shadow.setFont(new Font("serif", Font.PLAIN, 60));
        shadow.setForeground(Color.cyan);
        add(shadow);
          Timer headingTimer = new Timer(20, new ActionListener() {
            int x = heading.getX();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (x < 600) {
                    x++;
                } else {
                    x = 350;
                }
                heading.setLocation(x, heading.getY());
                shadow.setLocation(x + 4, shadow.getY());
            }
        });

        headingTimer.start();
        visitorNameLabel = new JLabel("Visitor Name:");
        visitorNameLabel.setBounds(520, 200, 100, 30);
        visitorNameField = new JTextField(20);
        visitorNameField.setBounds(770, 200, 270, 30);

        criminalNameLabel = new JLabel("Criminal Name:");
        criminalNameLabel.setBounds(520, 290, 100, 30);
        criminalNameField = new JTextField(20);
        criminalNameField.setBounds(770, 290, 270, 30);

        visitDateLabel = new JLabel("Visit Date (YYYY-MM-DD):");
        visitDateLabel.setBounds(520, 380, 150, 30);
        visitDateField = new JTextField(20);
        visitDateField.setBounds(770, 380, 270, 30);

        relationLabel = new JLabel("Relation:");
        relationLabel.setBounds(520, 470, 100, 30);
        relationField = new JTextField(20);
        relationField.setBounds(770, 470, 270, 30);

        contactNumberLabel = new JLabel("Contact Number:");
        contactNumberLabel.setBounds(520, 560, 120, 30);
        contactNumberField = new JTextField(20);
        contactNumberField.setBounds(770, 560, 270, 30);

        locationLabel = new JLabel("Address");
        locationLabel.setBounds(520, 650, 100, 30);
        locationField = new JTextField(20);
        locationField.setBounds(770, 650, 270, 30);

        addButton = new JButton("Add Visitation Record");
        addButton.setBackground(Color.cyan);
        addButton.setBounds(350, 730, 800, 30);
        
        backButton = new JButton("<");
        backButton.setBounds(10, 10, 80, 30);
        backButton.setForeground(Color.BLACK);  // Set text color
        backButton.setBackground(new Color(230,230,250));
        backButton.setBorderPainted(false);
        add(backButton);


        backButton.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current view
                new VisitorChoice().setVisible(true);
               
            }
        });

        setLayout(null);
        add(visitorNameLabel);
        add(visitorNameField);
        add(criminalNameLabel);
        add(criminalNameField);
        add(visitDateLabel);
        add(visitDateField);
        add(relationLabel);
        add(relationField);
        add(contactNumberLabel);
        add(contactNumberField);
        add(locationLabel);
        add(locationField);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVisitationRecord();
            }
        });
    }

    private void addVisitationRecord() {
        String visitorName = visitorNameField.getText();
        String criminalName = criminalNameField.getText();
        String visitDate = visitDateField.getText();
        String relation = relationField.getText();
        String contactNumber = contactNumberField.getText();
        String location = locationField.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", "");
            String insertQuery = "INSERT INTO visitation (visitor_name, criminal_name, visit_date, relation, contact_number, location) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(insertQuery);
            pstmt.setString(1, visitorName);
            pstmt.setString(2, criminalName);
            pstmt.setString(3, visitDate);
            pstmt.setString(4, relation);
            pstmt.setString(5, contactNumber);
            pstmt.setString(6, location);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();

            // Clear input fields
            visitorNameField.setText("");
            criminalNameField.setText("");
            visitDateField.setText("");
            relationField.setText("");
            contactNumberField.setText("");
            locationField.setText("");

            JOptionPane.showMessageDialog(this, "Visitation record added successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to add visitation record.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisitationApp app = new VisitationApp();
            app.setVisible(true);
        });
    }
}
