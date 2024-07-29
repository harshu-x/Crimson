import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMS extends JFrame {
    JLabel heading, mobileNumberLabel, messageLabel, statusLabel;
    JTextField mobileNumberField;
    JTextArea messageArea;
    JButton sendButton, clearButton;

    SMS() {
        heading = new JLabel("Send SMS");
        heading.setBounds(150, 20, 200, 30);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

        mobileNumberLabel = new JLabel("Mobile Number:");
        mobileNumberLabel.setBounds(50, 70, 150, 20);
        add(mobileNumberLabel);

        mobileNumberField = new JTextField();
        mobileNumberField.setBounds(200, 70, 200, 20);
        add(mobileNumberField);

        messageLabel = new JLabel("Message:");
        messageLabel.setBounds(50, 110, 150, 20);
        add(messageLabel);

        messageArea = new JTextArea();
        messageArea.setBounds(200, 110, 200, 100);
        add(messageArea);

        sendButton = new JButton("Send");
        sendButton.setBounds(100, 250, 100, 30);
        add(sendButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(220, 250, 100, 30);
        add(clearButton);

        statusLabel = new JLabel();
        statusLabel.setBounds(50, 300, 300, 30);
        add(statusLabel);

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mobileNumber = mobileNumberField.getText();
                String message = messageArea.getText();

                // Assuming the SMS sending logic here is successful
                // You should replace this with your actual logic
                boolean smsSentSuccessfully = true;

                if (smsSentSuccessfully) {
                    statusLabel.setText("SMS sent to registered mobile number successfully");
                    statusLabel.setForeground(Color.GREEN);
                } else {
                    statusLabel.setText("Failed to send SMS");
                    statusLabel.setForeground(Color.RED);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mobileNumberField.setText("");
                messageArea.setText("");
                statusLabel.setText("");
            }
        });

        setSize(450, 400);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SMS();
            }
        });
    }
}
