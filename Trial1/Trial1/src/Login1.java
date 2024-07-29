
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login1 extends JFrame implements ActionListener {
    JTextField username, password;
    JButton login, cancel;

    Login1() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel user = new JLabel("Username");
        user.setBounds(40, 20, 100, 30);
        add(user);
        username = new JTextField();
        username.setBounds(150, 20, 150, 30);
        add(username);

        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 70, 100, 30);
        add(pass);
        password = new JTextField();
        password.setBounds(150, 70, 150, 30);
        add(password);

        login = new JButton("Login");
        login.setBounds(40, 150, 120, 30);
        login.setBackground(Color.RED);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 150, 120, 30);
        cancel.setBackground(Color.RED);
        cancel.addActionListener(this);
        add(cancel);

        setBounds(500, 200, 600, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String user = username.getText();
            String pass = password.getText();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Use the updated driver class
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", ""); // Provide your correct username and password
                Statement st = con.createStatement();

                String query = "select * from adminlogin where username='" + user + "' and password='" + pass + "'";
                ResultSet rs = st.executeQuery(query);

                if (rs.next()) {
                    con.close(); // Close the database connection
                    setVisible(false);
                    // Add code to open your main application window here
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
        new Login1();
    }
}
