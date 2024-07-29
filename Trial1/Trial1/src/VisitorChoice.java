



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisitorChoice extends JFrame {
    JLabel heading;
    JButton backButton;
    
    VisitorChoice() {
        
        heading = new JLabel("VISITORS RECORDS HUB");
        heading.setBounds(350, -50, 800, 200);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLUE);        


        add(heading);

       
        JLabel shadow = new JLabel("VISITORS RECORD HUB");
        shadow.setBounds(heading.getX() + 4, heading.getY() + 4, heading.getWidth(), heading.getHeight());
        shadow.setFont(new Font("serif", Font.PLAIN, 60));
        shadow.setForeground(new Color(230,230,250));

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
        getContentPane().setBackground(new Color(255, 182, 193));
       // getContentPane().setBackground(new Color(230, 230, 250));
        setLayout(null);

        ImageIcon leftIcon = new ImageIcon(ClassLoader.getSystemResource("visitorsrecordadding.jpg"));
        JLabel leftImage = new JLabel(leftIcon);
        leftImage.setBounds(200, 300, 300, 300);
        add(leftImage);

        JButton leftButton = new JButton("Visitor Data Add");
        leftButton.setBounds(200, 610, 300, 40);
        leftButton.setBackground(Color.BLUE);
        leftButton.setForeground(Color.WHITE);
        add(leftButton);

        ImageIcon rightIcon = new ImageIcon(ClassLoader.getSystemResource("visitorlist.jpg"));
        JLabel rightImage = new JLabel(rightIcon);
        rightImage.setBounds(1060, 300, 300, 300);
        add(rightImage);

        JButton rightButton = new JButton("Visitor List");
        rightButton.setBounds(1060, 610, 300, 40);
        rightButton.setBackground(Color.BLUE);
        rightButton.setForeground(Color.WHITE);
        add(rightButton);

        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                new VisitationApp().setVisible(true); // Open the signup page
            }
        });

        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VisitorView().setVisible(true); 
            }
        });

        setSize(1920, 1080);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backButton = new JButton("<");
        backButton.setBounds(10, 10, 80, 30);
        backButton.setForeground(Color.BLACK);  // Set text color
        backButton.setBackground(new Color(255,182,193));
        backButton.setBorderPainted(false);
        add(backButton);


        backButton.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current view
                new AdminOptions(); // Open the AdminOptions page
            }
        });
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VisitorChoice().setVisible(true);
            }
        });
    }
}
