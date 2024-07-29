


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUserPage extends JFrame {
    JLabel heading;
    JButton backButton;
    
    
    AdminUserPage() {
        
        heading = new JLabel("WELCOME TO OUR PORTAL");
        heading.setBounds(350, -50, 800, 200);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLUE);        


        add(heading);

       
        JLabel shadow = new JLabel("WELCOME TO OUR PORTAL");
        shadow.setBounds(heading.getX() + 4, heading.getY() + 4, heading.getWidth(), heading.getHeight());
        shadow.setFont(new Font("serif", Font.PLAIN, 60));
        shadow.setForeground(Color.GREEN);

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
        getContentPane().setBackground(new Color(255, 182, 193));
       
        setLayout(null);

        ImageIcon leftIcon = new ImageIcon(ClassLoader.getSystemResource("userfinal.jpg"));
        JLabel leftImage = new JLabel(leftIcon);
        leftImage.setBounds(200, 300, 300, 300);
        add(leftImage);

       JButton leftButton = new JButton("User");
       leftButton.setBounds(200, 610, 300, 40);
       leftButton.setForeground(Color.WHITE);
       leftButton.setBackground(Color.BLUE);
       add(leftButton);

        ImageIcon rightIcon = new ImageIcon(ClassLoader.getSystemResource("adminfinal1.jpg"));
        JLabel rightImage = new JLabel(rightIcon);
        rightImage.setBounds(1060, 300, 300, 300);
        add(rightImage);

        JButton rightButton = new JButton("Admin");
        rightButton.setBounds(1060, 610, 300, 40);
         rightButton.setForeground(Color.WHITE);
       rightButton.setBackground(Color.GREEN);
        add(rightButton);

        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new signup().setVisible(true); 
            }
        });

        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new AdminLogin().setVisible(true); 
            }
        });
         backButton = new JButton("<");
        backButton.setBounds(10, 10, 80, 30);
        backButton.setForeground(Color.BLACK);
      backButton.setBackground(new Color(255, 182, 193));
        backButton.setBorderPainted(false);
        add(backButton);


        backButton.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current view
                new LandingPage().setVisible(true);
            }
        });

        setSize(1920, 1080);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminUserPage().setVisible(true);
            }
        });
    }
}
