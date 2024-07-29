import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddCriminalRecord extends JFrame implements ActionListener {
    private static Connection conn;

    JTextField nameF, ageF, nationalityF, addressF, crimeF, casesF, bailStatusF,aadharF, phnoF, emailF;
    JRadioButton maleB, femaleB;
    JButton saveB, exitB, backButton;
     JLabel movingText;
     JLabel heading;
    Timer textAnimationTimer;
    int movingTextX;

    public AddCriminalRecord() {
        
        setLayout(null);
        setSize(1920, 1080);
        setLocation(0, 0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       heading = new JLabel("Adding Criminal Record");
        heading.setBounds(350, -65, 800, 200);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel shadow = new JLabel("Adding Criminal Record");
        shadow.setBounds(heading.getX() + 4, heading.getY() + 4, heading.getWidth(), heading.getHeight());
        shadow.setFont(new Font("serif", Font.PLAIN, 60));
        shadow.setForeground(new Color(255,182,193));
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
        JPanel image = new JPanel();
        image.setLayout(null);
        image.setBounds(0, 0, 1920, 1080);
        image.setBackground(new Color(250, 230, 250));
        add(image);

        JLabel name = new JLabel("Name:");
        name.setBounds(250, 100, 120, 28);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(name);

        nameF = new JTextField(20);
        nameF.setBounds(500, 100, 300, 28);
        nameF.setForeground(Color.darkGray);
        nameF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(nameF);

        JLabel age = new JLabel("Age:");
        age.setBounds(250, 160, 120, 28);
        age.setForeground(Color.BLACK);
        age.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(age);

        ageF = new JTextField(20);
        ageF.setBounds(500, 160, 300, 28);
        ageF.setForeground(Color.darkGray);
        ageF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(ageF);

        JLabel gender = new JLabel("Gender:");
        gender.setBounds(250, 220, 120, 20);
        gender.setForeground(Color.BLACK);
        gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(gender);

        ButtonGroup genderBtn = new ButtonGroup();

        maleB = new JRadioButton("Male");
        maleB.setBounds(500, 220, 70, 20);
        maleB.setOpaque(false);
        maleB.setForeground(Color.BLACK);
        maleB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(maleB);
//
        femaleB = new JRadioButton("Female");
        femaleB.setBounds(650, 220, 100, 20);
        femaleB.setOpaque (false);
        femaleB.setForeground(Color.BLACK);
        femaleB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(femaleB);
//
        genderBtn.add(maleB);
        genderBtn.add(femaleB);

        JLabel nationality = new JLabel("Nationality:");
        nationality.setBounds(250, 280, 120, 28);
        nationality.setForeground(Color.BLACK);
        nationality.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(nationality);

        nationalityF = new JTextField(20);
        nationalityF.setBounds(500, 280, 300, 28);
        nationalityF.setForeground(Color.darkGray);
        nationalityF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(nationalityF);

        JLabel address = new JLabel("Address:");
        address.setBounds(250, 340, 120, 28);
        address.setForeground(Color.BLACK);
        address.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(address);

        addressF = new JTextField(20);
        addressF.setBounds(500, 340, 300, 28);
        addressF.setForeground(Color.darkGray);
        addressF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(addressF);
        
        JLabel aadhar = new JLabel("Aadhar no:");
        aadhar.setBounds(250, 400, 120, 28);
        aadhar.setForeground(Color.BLACK);
        aadhar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(aadhar);
        
        aadharF = new JTextField(20);
        aadharF.setBounds(500, 400, 300, 28);
        aadharF.setForeground(Color.darkGray);
        aadharF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(aadharF);

        JLabel phno = new JLabel("Phone no:");
        phno.setBounds(250, 460, 120, 28);
        phno.setForeground(Color.BLACK);
        phno.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(phno);

        phnoF = new JTextField(20);
        phnoF.setBounds(500, 460, 300, 28);
        phnoF.setForeground(Color.darkGray);
        phnoF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(phnoF);
        
        JLabel email = new JLabel("E-mail:");
        email.setBounds(250, 520, 120, 28);
        email.setForeground(Color.BLACK);
        email.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(email);

        emailF = new JTextField(20);
        emailF.setBounds(500, 520, 300, 28);
        emailF.setForeground(Color.darkGray);
        emailF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(emailF);

        JLabel crime = new JLabel("Crime:");
        crime.setBounds(250, 580, 120, 28);
        crime.setForeground(Color.BLACK);
        crime.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(crime);

        crimeF = new JTextField(20);
        crimeF.setBounds(500, 580, 300, 28);
        crimeF.setForeground(Color.darkGray);
        crimeF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(crimeF);

        JLabel cases = new JLabel("No of cases:");
        cases.setBounds(250, 640, 150, 28);
        cases.setForeground(Color.BLACK);
        cases.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(cases);

        casesF = new JTextField(20);
        casesF.setBounds(500, 640, 300, 28);
        casesF.setForeground(Color.darkGray);
        casesF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(casesF);

        JLabel bailYear = new JLabel("Bail Status:");
        bailYear.setBounds(250, 720, 120, 28);
        bailYear.setForeground(Color.BLACK);
        bailYear.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(bailYear);

        bailStatusF = new JTextField(20);
        bailStatusF.setBounds(500, 720, 300, 28);
        bailStatusF.setForeground(Color.darkGray);
        bailStatusF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(bailStatusF);

        saveB = new JButton("Save");
        saveB.setBounds(1000, 650, 175, 35);
        saveB.setBackground(new Color(255,182,193));
        saveB.setForeground(Color.BLUE);
        saveB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        saveB.addActionListener(this);
        image.add(saveB);

        exitB = new JButton("Exit");
        exitB.setBounds(1280, 650, 175, 35);
        exitB.setBackground(Color.BLUE);
        exitB.setForeground(new Color(255,182,193));
        exitB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        exitB.addActionListener(this);
        image.add(exitB);

        backButton = new JButton("<");
        backButton.setBounds(10, 10, 80, 30);
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(new Color(250, 230, 250));
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminOptions();
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        conn = con();
        if (conn != null) {
            SwingUtilities.invokeLater(() -> {
                new AddCriminalRecord();
            });
        } else {
            JOptionPane.showMessageDialog(null, "Error connecting to the database.");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == saveB) {
            String name = nameF.getText();
            String age = ageF.getText();
            String gender = null;
            if (maleB.isSelected()) {
                gender = "Male";
            } else if (femaleB.isSelected()) {
                gender = "Female";
            }
            String nationality = nationalityF.getText();
            String address = addressF.getText();
             String aadhar = aadharF.getText();
            String phno = phnoF.getText();
            String email = emailF.getText();
            String crime = crimeF.getText();
            String cases = casesF.getText();
            String bailStatus = bailStatusF.getText();

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", "");
                String insertQuery = "INSERT INTO adding(name, age, gender, nationality, address,aadhar,phone,email ,crime, cases, bailStatus) VALUES (?,?,?,?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(insertQuery);
                ps.setString(1, name);
                ps.setString(2, age);
                ps.setString(3, gender);
                ps.setString(4, nationality);
                ps.setString(5, address);
                ps.setString(6, aadhar);
                ps.setString(7,phno);
                ps.setString(8,email);
                ps.setString(9,crime);
                ps.setString(10, cases);
                ps.setString(11, bailStatus);
                ps.executeUpdate();
                ps.close();
                connection.close();
                nationalityF.setText("");
                addressF.setText("");
              aadharF.setText("");
            phnoF.setText("");
             emailF.setText("");
            crimeF.setText("");
            casesF.setText("");
             bailStatusF.setText("");
                
                 JOptionPane.showMessageDialog(this, "Visitation record added successfully.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: Unable to add visitation record.");
            }
        } else if (ae.getSource() == exitB) {
            System.exit(0);
        }
    }
//
//    public static Connection con() {
//        Connection con = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", "");
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        return con;
//    }
}
