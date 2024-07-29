import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class LandingPage extends JFrame {
    JLabel heading, shadowLabel;
    int x, y;
    Timer timer;

    LandingPage() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        x = 80;
        y = 230;

        shadowLabel = new JLabel("WELCOME TO CRIMSON");
        shadowLabel.setBounds(x + 2, y + 2, 1200, 60);
        shadowLabel.setFont(new Font("serif", Font.PLAIN, 60));
        shadowLabel.setForeground(Color.BLUE);
        add(shadowLabel);
 
        heading = new JLabel("WELCOME TO CRIMSON");
        heading.setBounds(x, y, 1200, 60);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.GREEN);
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Langin.jpg"));
        Image i2 = i1.getImage().getScaledInstance(32570, 850, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 3170, 850);
        add(image);

        JButton clickhere = new JButton("CLICK HERE TO CONTINUE");
        clickhere.setBounds(600, 400, 300, 70);
        clickhere.setBackground(Color.BLACK);
        clickhere.setForeground(Color.WHITE);

        clickhere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminUserPage adminUserPage = new AdminUserPage();
                adminUserPage.setVisible(true);
                dispose();
            }
        });
        add(clickhere);
        setSize(1920, 1080);
        setLocation(0, 0);
        setVisible(true);

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                x += 2;
                if (x > getWidth()) {
                    x = -heading.getWidth();
                }
                shadowLabel.setLocation(x + 2, y + 2);
                heading.setLocation(x, y);
            }
        });
        timer.start();
    }

    public static void main(String args[]) {
        new LandingPage();
    }
}
