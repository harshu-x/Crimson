import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class UpdateCriminalRecords extends JFrame implements ActionListener {
    private static Connection conn;

    JTextField searchNameF, nameF, ageF, genderF, nationalityF, addressF, crimeF, casesF, bailStatusF, aadharF, phnoF, emailF;

    JLabel heading;
    JButton searchB, updateB, exitB, backButton;

    public UpdateCriminalRecords() {
        setLayout(null);
        setSize(1920, 1080);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Header with animation
        heading = new JLabel("Update Criminal Record");
        heading.setBounds(350, -65, 800, 200);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel shadow = new JLabel("Update Criminal Record");
        shadow.setBounds(heading.getX() + 4, heading.getY() + 4, heading.getWidth(), heading.getHeight());
        shadow.setFont(new Font("serif", Font.PLAIN, 60));
        shadow.setForeground(new Color(255, 182, 193));
        add(shadow);

        Timer headingTimer = new Timer(30, new ActionListener() {
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

        JPanel image = new JPanel();
        image.setLayout(null);
        image.setBounds(0, 0, 1920, 1080);
        image.setBackground(new Color(250, 230, 250));
        add(image);

        // Input Fields and Labels
        JLabel searchLabel = createLabel(image, "Search by Name:", 1020, 70, 200, 30);
searchNameF = createTextField(image, 1020, 110, 200, 30);




        searchB = createButton(image, "Search By Name", 1280, 110, 180, 30);
        searchB.addActionListener(this);

        nameF = createLabeledField(image, "Name:", 250, 120);
        ageF = createLabeledField(image, "Age:", 250, 180);
        genderF = createLabeledField(image, "Gender:", 250, 240);
        nationalityF = createLabeledField(image, "Nationality:", 250, 300);
        addressF = createLabeledField(image, "Address:", 250, 360);
        aadharF = createLabeledField(image, "Aadhar No:", 250, 420);
        phnoF = createLabeledField(image, "Phone No:", 250, 480);
        emailF = createLabeledField(image, "Email:", 250, 540);
        crimeF = createLabeledField(image, "Crime:", 250, 600);
        casesF = createLabeledField(image, "No of Cases:", 250, 660);
        bailStatusF = createLabeledField(image, "Bail Status:", 250, 720);

        updateB = createButton(image, "Update", 1050, 600, 180, 35);
        updateB.addActionListener(this);

        exitB = createButton(image, "Exit", 1300, 600, 180, 35);
        exitB.addActionListener(this);

        backButton = createButton(this, "<", 10, 10, 80, 30);
        backButton.addActionListener(e -> {
            new AdminOptions();
            setVisible(false);
        });

        setVisible(true);
    }

    // Utility methods to create components
    private JTextField createLabeledField(JPanel panel, String label, int x, int y) {
        JLabel lbl = createLabel(panel, label, x, y - 10, 120, 28);
        return createTextField(panel, x + 170, y - 10, 300, 28);
    }

    private JLabel createLabel(JPanel panel, String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel.add(label);
        return label;
    }

    private JTextField createTextField(JPanel panel, int x, int y, int width, int height) {
        JTextField field = new JTextField(20);
        field.setBounds(x, y, width, height);
        field.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel.add(field);
        return field;
    }

    private JButton createButton(Container panel, String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel.add(button);
        return button;
    }

    // Database Connection
    public static Connection con() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchB) {
            searchRecord();
        } else if (ae.getSource() == updateB) {
            updateRecord();
        } else if (ae.getSource() == exitB) {
            System.exit(0);
        }
    }

    private void searchRecord() {
        String searchName = searchNameF.getText();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM adding WHERE name=?");
            ps.setString(1, searchName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nameF.setText(rs.getString("name"));
                ageF.setText(rs.getString("age"));
                genderF.setText(rs.getString("gender"));
                nationalityF.setText(rs.getString("nationality"));
                addressF.setText(rs.getString("address"));
                aadharF.setText(rs.getString("aadhar"));
                phnoF.setText(rs.getString("phone"));
                emailF.setText(rs.getString("email"));
                crimeF.setText(rs.getString("crime"));
                casesF.setText(rs.getString("cases"));
                bailStatusF.setText(rs.getString("bailStatus"));
            } else {
                JOptionPane.showMessageDialog(null, "Record not found.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        }
    }

    private void updateRecord() {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE adding SET age=?, gender=?, nationality=?, address=?, aadhar=?, phone=?, email=?, crime=?, cases=?, bailStatus=? WHERE name=?"
            );
            ps.setString(1, ageF.getText());
            ps.setString(2, genderF.getText());
            ps.setString(3, nationalityF.getText());
            ps.setString(4, addressF.getText());
            ps.setString(5, aadharF.getText());
            ps.setString(6, phnoF.getText());
            ps.setString(7, emailF.getText());
            ps.setString(8, crimeF.getText());
            ps.setString(9, casesF.getText());
            ps.setString(10, bailStatusF.getText());
            ps.setString(11, nameF.getText());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Record updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Error updating record.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        conn = con();
        if (conn != null) {
            SwingUtilities.invokeLater(UpdateCriminalRecords::new);
        } else {
            JOptionPane.showMessageDialog(null, "Database connection failed.");
        }
    }
}
