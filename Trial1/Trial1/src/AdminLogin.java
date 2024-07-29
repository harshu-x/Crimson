import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLogin extends JFrame implements ActionListener {
    JTextField username, password;
    JButton login, cancel,backButton;
    JLabel heading, shadowLabel,movingText;
     int movingTextX;

    AdminLogin() {
        getContentPane().setBackground(new Color(230, 230, 250)); // Set the background color
        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);

        int x = 550;
        int y = 50;

        shadowLabel = new JLabel("ADMIN LOGIN");
        shadowLabel.setBounds(x + 2, y + 2, 1200, 60);
        shadowLabel.setFont(new Font("serif", Font.PLAIN, 60));
        shadowLabel.setForeground(Color.BLUE);
        add(shadowLabel);

        heading = new JLabel("ADMIN LOGIN");
        heading.setBounds(x, y, 1200, 60);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.GREEN);
        add(heading);
        
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
                shadowLabel.setLocation(x + 4, shadowLabel.getY());
            }
        });

        headingTimer.start();

        JLabel description = new JLabel("Please enter your admin credentials to log in.");
        description.setBounds(450, 240, 400, 30);
        description.setFont(new Font("Arial", Font.ITALIC, 14));
        description.setForeground(Color.RED);
        add(description);

        JLabel user = new JLabel("Username");
        user.setBounds(360, 290, 100, 30);
        add(user);
        username = new JTextField();
        username.setBounds(460, 290, 250, 30);
        add(username);

        JLabel pass = new JLabel("Password");
        pass.setBounds(360, 360, 100, 30);
        add(pass);
        password = new JPasswordField();
        password.setBounds(460, 360, 250, 30);
        add(password);

        login = new JButton("Login");
        login.setBounds(360, 480, 120, 30);
        login.setBackground(Color.BLUE);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(590, 480, 120, 30);
        cancel.setBackground(Color.BLUE);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);
        
           backButton = new JButton("<"); // Add Back button
        backButton.setBounds(30, 30, 50, 30); 
          backButton.setBackground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                setVisible(false);
                new AdminUserPage().setVisible(true); // Replace with the appropriate page for AdminUserPage
            }
        });
        add(backButton);
        
        movingText = new JLabel("\"Empowering Justice: Admin Access Portal to Unlock the Gateway to Crime Management\"");
        movingText.setBounds(10, 750, 1920, 30);
        movingText.setFont(new Font("Serif", Font.PLAIN, 20));
        movingText.setForeground(Color.GRAY);

        add(movingText);

        // Create a Timer for the moving text animation
        movingTextX = movingText.getX();
        Timer textTimer = new Timer(2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (movingTextX < 1920) {
                    movingTextX++;
                } else {
                    movingTextX = -800;
                }
                movingText.setLocation(movingTextX, movingText.getY());
            }
        });

        textTimer.start();

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("adminfinal.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(890, 300, 340, 220);
        add(image);

        
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        setBounds(0, 0, 1920, 1080);
        setVisible(true);
    }


    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String user = username.getText();
            String pass = new String(password.getText()); // Retrieve password as a char array

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", ""); // Provide your correct username and password
                Statement st = con.createStatement();

                String query = "select * from adminlogin where username='" + user + "' and password='" + pass + "'";
                ResultSet rs = st.executeQuery(query);

                if (rs.next()) {
                    con.close();
                    setVisible(false);
                    new AdminOptions();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }


    public static void main(String args[]) {
        new AdminLogin();
    }
}