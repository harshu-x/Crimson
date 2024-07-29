
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminOptions extends JFrame {

    JLabel heading, movingText;
    int movingTextX;
    JButton backButton;

    AdminOptions() {
        getContentPane().setBackground(new Color(255, 182, 193));
        setLayout(null);

        heading = new JLabel("ADMIN CONTROL PANEL");
        heading.setBounds(350, -65, 800, 200);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel shadow = new JLabel("ADMIN CONTROL PANEL");
        shadow.setBounds(heading.getX() + 4, heading.getY() + 4, heading.getWidth(), heading.getHeight());
        shadow.setFont(new Font("serif", Font.PLAIN, 60));
        shadow.setForeground(new Color(230,230,250));
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

        movingText = new JLabel("This is the control panel for administrators who have the right to see the complaints filed, add new records, update existing records, and see visitors list and records");
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

        ImageIcon leftIcon1 = new ImageIcon(ClassLoader.getSystemResource("adminfinal1.jpg"));
        JLabel leftImage1 = new JLabel(leftIcon1);
        leftImage1.setBounds(620, 100, 250, 250);
        add(leftImage1);

        JButton leftButton1 = new JButton("Add Record");
        leftButton1.setBounds(620, 362, 250, 40);
        leftButton1.setBackground(Color.BLUE);
        leftButton1.setForeground(Color.WHITE);
        add(leftButton1);

        ImageIcon extra1 = new ImageIcon(ClassLoader.getSystemResource("complaints.jpg"));
        
        JLabel extraImage1 = new JLabel(extra1);
        extraImage1.setBounds(150, 100, 250, 250);
        add(extraImage1);

        JButton extra = new JButton("View complaints");
        extra.setBounds(150, 362, 250, 40);
        extra.setBackground(Color.BLUE);
        extra.setForeground(Color.WHITE);
        add(extra);

        ImageIcon rightIcon1 = new ImageIcon(ClassLoader.getSystemResource("admin3.jpg"));
        JLabel rightImage1 = new JLabel(rightIcon1);
        rightImage1.setBounds(1080, 100, 250, 250);
        add(rightImage1);

        JButton rightButton1 = new JButton("Update Record");
         rightButton1.setBackground(Color.BLUE);
        rightButton1.setForeground(Color.WHITE);
        rightButton1.setBounds(1080, 362, 250, 40);
        add(rightButton1);

        ImageIcon leftIcon2 = new ImageIcon(ClassLoader.getSystemResource("view.jpg"));
        JLabel leftImage2 = new JLabel(leftIcon2);
        leftImage2.setBounds(370, 450, 250, 250);
        add(leftImage2);

        JButton leftButton2 = new JButton("Criminal Records");
        leftButton2.setBounds(370, 710, 250, 40);
         leftButton2.setBackground(Color.BLUE);
        leftButton2.setForeground(Color.WHITE);
        add(leftButton2);

        ImageIcon rightIcon2 = new ImageIcon(ClassLoader.getSystemResource("visito.png"));
        JLabel rightImage2 = new JLabel(rightIcon2);
        rightImage2.setBounds(840, 450, 250, 250);
        add(rightImage2);

        JButton rightButton2 = new JButton("Visitor Records");
        rightButton2.setBackground(Color.BLUE);
        rightButton2.setForeground(Color.WHITE);
        rightButton2.setBounds(840, 710, 250, 40);
        add(rightButton2);

        leftButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddCriminalRecord a = new AddCriminalRecord();
                a.setVisible(true);
                dispose();
            }
        });
          extra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RequestPage r = new RequestPage();
                r.setVisible(true);
                dispose();
            }
        });


        rightButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateCriminalRecords q=new UpdateCriminalRecords();
                q.setVisible(true);
                dispose();
            }
        });

        leftButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                View view = new View();
                view.setVisible(true);
                dispose();
            }
        });

        rightButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VisitorChoice v = new VisitorChoice();
                v.setVisible(true);
                dispose();
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
                new AdminLogin().setVisible(true);
            }
        });
        setBounds(0, 0, 1920, 1080);
        setSize(1920, 1080);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminOptions();
    }
}
