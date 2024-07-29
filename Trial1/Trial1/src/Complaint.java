
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.io.*;

public class Complaint extends JFrame implements ActionListener {

    private static Connection conn;

    JTextField titleF, descriptionF, nameF, emailF, phoneNumberF;
    JLabel nameLabel, emailLabel, phoneNumberLabel, descriptionLabel, titlelabel, complaintIdLabel, statusLabel;
    JButton registerButton, exitButton, evidenceButton, backButton;
    String evidenceFilePath;
    JLabel evidenceLabel, heading;

    public Complaint() {
        setLayout(null);
        setSize(1920, 1080);
        setLocation(0, 0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(230, 230, 250)); // Light Yellow

        heading = new JLabel("FILE A COMPLAINT");
        heading.setBounds(300, -50, 800, 200);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel shadow = new JLabel("FILE A COMPLAINT");
        shadow.setBounds(heading.getX() + 4, heading.getY() + 4, heading.getWidth(), heading.getHeight());
        shadow.setFont(new Font("serif", Font.PLAIN, 60));
        shadow.setForeground(new Color(255, 182, 193));
        add(shadow);
        
        Timer headingTimer = new Timer(20, new ActionListener() {
            int x = heading.getX();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (x < 400) {
                    x++;
                } else {
                    x = 350;
                }
                heading.setLocation(x, heading.getY());
                shadow.setLocation(x + 4, shadow.getY());
            }
        });

        headingTimer.start();

        titlelabel = new JLabel("Title:");
        titlelabel.setBounds(350, 230, 150, 30);
        add(titlelabel);
        titleF = new JTextField(20);
        titleF.setBounds(550, 230, 200, 30);
        add(titleF);

        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(350, 540, 100, 20);
        add(descriptionLabel);
        descriptionF = new JTextField(20);
        descriptionF.setBounds(550, 540, 400, 60);
        add(descriptionF);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(350, 305, 150, 30);
        add(nameLabel);
        nameF = new JTextField(20);
        nameF.setBounds(550, 305, 200, 30);
        add(nameF);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(350, 380, 100, 30);
        add(emailLabel);
        emailF = new JTextField(20);
        emailF.setBounds(550, 380, 200, 30);
        add(emailF);

        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(350, 455, 150, 30);
        add(phoneNumberLabel);
        phoneNumberF = new JTextField(20);
        phoneNumberF.setBounds(550, 455, 200, 30);
        add(phoneNumberF);

        evidenceButton = new JButton("Attach Evidence");
        evidenceButton.setBounds(350, 680, 150, 30);
        evidenceButton.setBackground(Color.BLUE);
        evidenceButton.setForeground(Color.WHITE);
        evidenceButton.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 16));
        evidenceButton.addActionListener(this);
        add(evidenceButton);
        evidenceLabel = new JLabel("Evidence: ");
        evidenceLabel.setBounds(350, 710, 700, 20);
        add(evidenceLabel);

        registerButton = new JButton("Register Complaint");
        registerButton.setBounds(550, 680, 200, 30);
        registerButton.setBackground(Color.BLUE);
        registerButton.setForeground(Color.WHITE);
      //  registerButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        registerButton.addActionListener(this);
        add(registerButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(800, 680, 200, 30);
         exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
      //  exitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        exitButton.addActionListener(this);
        add(exitButton);

        backButton = new JButton("<");
        backButton.setBounds(10, 10, 80, 30);
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(new Color(230, 230, 250));
        backButton.setBorderPainted(false);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current view
                new AdminUserPage().setVisible(true);
            }
        });
    }

    public static Connection con() {
        Connection con = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", "");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found: " + e.getMessage());
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error connecting to the database: " + e.getMessage());
    }
    return con;
    }

    public static void main(String[] args) {
        conn = con();
        if (conn != null) {
            SwingUtilities.invokeLater(() -> {
                new Complaint();
            });
        } else {
            JOptionPane.showMessageDialog(null, "Error connecting to the database.");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == registerButton) {
            String title = titleF.getText();
            String description = descriptionF.getText();
            String name = nameF.getText();
            String email = emailF.getText();
            String phoneNumber = phoneNumberF.getText();
            LocalDateTime registrationDateTime = LocalDateTime.now();

            try {
                File evidenceFile = new File(evidenceFilePath);
                FileInputStream fis = new FileInputStream(evidenceFile);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }

                byte[] evidenceBytes = bos.toByteArray();
                  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", "");
               String insertQuery ="INSERT INTO complaints (title, name, email, phone, registration_datetime, description, evidence) VALUES(?,?,?,?,?,?,?)";
               PreparedStatement ps = connection.prepareStatement(insertQuery);
                ps.setString(1, title);
                ps.setString(2, name);
                ps.setString(3, email);
                ps.setString(4, phoneNumber);
                ps.setObject(5, registrationDateTime);
                ps.setString(6, description);
                ps.setBytes(7, evidenceBytes);
                
//                int result = ps.executeUpdate();
//
//                if (result > 0) {
//                    ResultSet generatedKeys = ps.getGeneratedKeys();
//                    if (generatedKeys.next()) {
//                        int complaintId = generatedKeys.getInt(1);
//                        JOptionPane.showMessageDialog(null, "Complaint registered successfully. Complaint ID: " + complaintId);
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Error getting complaint ID.");
//                    }
//                    titleF.setText("");
//                    descriptionF.setText("");
//                    nameF.setText("");
//                    emailF.setText("");
//                    phoneNumberF.setText("");
//                    evidenceFilePath = null;
//                    evidenceLabel.setText("Evidence File: ");
//                } else {
//                    JOptionPane.showMessageDialog(null, "Error registering complaint.");
//                }
                        ps.executeUpdate();
                ps.close();
            connection.close();
                titleF.setText("");
                    descriptionF.setText("");
                    nameF.setText("");
                    emailF.setText("");
                    phoneNumberF.setText("");
                    evidenceFilePath = null;
                    evidenceLabel.setText("Evidence File: ");
                     JOptionPane.showMessageDialog(this, "Visitation record added successfully.");
            } catch (SQLException | IOException ex) {
                 ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Error: Unable to add visitation record.");
            }
        } else if (ae.getSource() == exitButton) {
            System.exit(0);
        } else if (ae.getSource() == evidenceButton) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                evidenceFilePath = selectedFile.getAbsolutePath();
                evidenceLabel.setText("Evidence File: " + evidenceFilePath);
            }
        }
    }
}
