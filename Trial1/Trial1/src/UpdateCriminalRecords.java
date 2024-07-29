import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class UpdateCriminalRecords extends JFrame implements ActionListener {
    private static Connection conn;

    JTextField searchNameF, nameF, ageF, genderF, nationalityF, addressF, crimeF, casesF, bailStatusF,aadharF, phnoF, emailF;
JLabel movingText;
     JLabel heading;
    Timer textAnimationTimer;
    int movingTextX;

    JButton searchB, updateB, exitB, backButton;

    public UpdateCriminalRecords() {
        setLayout(null);
        setSize(1920, 1080);
        setLocation(0, 0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          heading = new JLabel("Update Criminal Record");
        heading.setBounds(350, -65, 800, 200);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel shadow = new JLabel("Update  Criminal Record");
        shadow.setBounds(heading.getX() + 4, heading.getY() + 4, heading.getWidth(), heading.getHeight());
        shadow.setFont(new Font("serif", Font.PLAIN, 60));
        shadow.setForeground(new Color(255,182,193));
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

  

        searchNameF = new JTextField(20);
        searchNameF.setBounds(1020, 110, 200, 30);
        searchNameF.setForeground(Color.darkGray);
        searchNameF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(searchNameF);

        searchB = new JButton("Search By Name");
        searchB.setBounds(1280, 110, 180, 30);
        searchB.setBackground(Color.RED);
        searchB.setForeground(Color.WHITE);
       
        searchB.addActionListener(this);
        image.add(searchB);

        JLabel name = new JLabel("Name:");
        name.setBounds(250, 120, 120, 28);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(name);

        nameF = new JTextField(20);
        nameF.setBounds(420, 120, 300, 28);
        nameF.setForeground(Color.darkGray);
        nameF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(nameF);

        JLabel age = new JLabel("Age:");
        age.setBounds(250, 180, 120, 25);
        age.setForeground(Color.BLACK);
        age.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(age);

        ageF = new JTextField(20);
        ageF.setBounds(420, 180, 300, 28);
        ageF.setForeground(Color.darkGray);
        ageF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(ageF);

        JLabel gender = new JLabel("Gender:");
        gender.setBounds(250, 240, 120, 28);
        gender.setForeground(Color.BLACK);
        gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(gender);

        genderF = new JTextField(20);
        genderF.setBounds(420, 240, 300, 28);
        genderF.setForeground(Color.darkGray);
        genderF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(genderF);

        JLabel nationality = new JLabel("Nationality:");
        nationality.setBounds(250, 300, 120, 28);
        nationality.setForeground(Color.BLACK);
        nationality.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(nationality);

        nationalityF = new JTextField(20);
        nationalityF.setBounds(420, 300, 300, 28);
        nationalityF.setForeground(Color.darkGray);
        nationalityF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(nationalityF);

        JLabel address = new JLabel("Address:");
        address.setBounds(250, 360, 120, 28);
        address.setForeground(Color.BLACK);
        address.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(address);

        addressF = new JTextField(20);
        addressF.setBounds(420, 360, 300, 28);
        addressF.setForeground(Color.darkGray);
        addressF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(addressF);
        
        
        JLabel aadhar = new JLabel("Aadhar no:");
        aadhar.setBounds(250, 420, 120, 28);
        aadhar.setForeground(Color.BLACK);
        aadhar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(aadhar);
        
        aadharF = new JTextField(20);
        aadharF.setBounds(420, 420, 300, 28);
        aadharF.setForeground(Color.darkGray);
        aadharF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(aadharF);

        JLabel phno = new JLabel("Phone no:");
        phno.setBounds(250, 480, 120, 28);
        phno.setForeground(Color.BLACK);
        phno.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(phno);

        phnoF = new JTextField(20);
        phnoF.setBounds(420, 480, 300, 28);
        phnoF.setForeground(Color.darkGray);
        phnoF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(phnoF);
        
        JLabel email = new JLabel("E-mail:");
        email.setBounds(250, 540, 120, 28);
        email.setForeground(Color.BLACK);
        email.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(email);

        emailF = new JTextField(20);
        emailF.setBounds(420, 540, 300, 28);
        emailF.setForeground(Color.darkGray);
        emailF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(emailF);

        JLabel crime = new JLabel("Crime:");
        crime.setBounds(250, 600, 120, 28);
        crime.setForeground(Color.BLACK);
        crime.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(crime);

        crimeF = new JTextField(20);
        crimeF.setBounds(420, 600, 300, 28);
        crimeF.setForeground(Color.darkGray);
        crimeF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(crimeF);

        JLabel cases = new JLabel("No of cases:");
        cases.setBounds(250, 660, 150, 28);
        cases.setForeground(Color.BLACK);
        cases.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(cases);

        casesF = new JTextField(20);
        casesF.setBounds(420, 660, 300, 28);
        casesF.setForeground(Color.darkGray);
        casesF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(casesF);

        JLabel bailStatus = new JLabel("Bail Status:");
        bailStatus.setBounds(250, 720, 120, 28);
        bailStatus.setForeground(Color.BLACK);
        bailStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(bailStatus);

        bailStatusF = new JTextField(20);
        bailStatusF.setBounds(420, 720, 300, 28);
        bailStatusF.setForeground(Color.darkGray);
        bailStatusF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        image.add(bailStatusF);

        updateB = new JButton("Update");
        updateB.setBounds(1050, 600, 180, 35);
        updateB.setBackground(new Color(255,182,193));
        updateB.setForeground(Color.BLUE);
        updateB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        updateB.addActionListener(this);
        image.add(updateB);

        exitB = new JButton("Exit");
        exitB.setBounds(1300, 600, 180, 35);
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
                new UpdateCriminalRecords();
            });
        } else {
            JOptionPane.showMessageDialog(null, "Error connecting to the database.");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchB) {
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
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL error: " + ex.getMessage());
            }
        } else if (ae.getSource() == updateB) {
            // Get values from input fields
            String name = nameF.getText();
            String age = ageF.getText();
            String gender = genderF.getText();
            String nationality = nationalityF.getText();
            String address = addressF.getText();
            String aadhar=aadharF.getText();
            String phone=phnoF.getText();
            String email=emailF.getText();
            String crime = crimeF.getText();
            String cases = casesF.getText();
            String bailStatus = bailStatusF.getText();

            try {
                PreparedStatement ps = conn.prepareStatement(
                    "UPDATE adding SET name=?, age=?, gender=?, nationality=?, address=?,aadhar=?,phone=?,email=?, crime=?, cases=?, bailStatus=? WHERE name=?"
                );
                ps.setString(1, name);
                ps.setString(2, age);
                ps.setString(3, gender);
                ps.setString(4, nationality);
                ps.setString(5, address);
                ps.setString(6,aadhar);
                ps.setString(7,phone);
                ps.setString(8,email);
                ps.setString(9, crime);
                ps.setString(10, cases);
                ps.setString(11, bailStatus);
                ps.setString(12, searchNameF.getText());

                int result = ps.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Criminal record successfully updated");
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating criminal record");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL error: " + ex.getMessage());
            }
        } else if (ae.getSource() == exitB) {
            System.exit(0);
        }
    }

    public static Connection con() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }
}
