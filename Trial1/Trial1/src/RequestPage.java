
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequestPage extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JLabel heading;
    private JButton backButton;
    private JTextField nameSearchField;

    RequestPage() {
        getContentPane().setBackground(new Color(230, 230, 250));
        setLayout(null);
        setBounds(0, 0, 1920, 1080);
        setVisible(true);

        heading = new JLabel("Complaints Registered");
        heading.setBounds(330, -10, 800, 200);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel shadow = new JLabel("Complaints Registered");
        shadow.setBounds(heading.getX() + 4, heading.getY() + 4, heading.getWidth(), heading.getHeight());
        shadow.setFont(new Font("serif", Font.PLAIN, 60));
        shadow.setForeground(Color.PINK);
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

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 150, 1920, 700);
        add(scrollPane);

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane.setViewportView(table);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", "");

            Statement st = con.createStatement();

            // Initial query to load all complaints
            String initialQuery = "SELECT * FROM Complaints";
            ResultSet rs = st.executeQuery(initialQuery);
            model = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);

            table.setModel(model);

            JTableHeader header = table.getTableHeader();
            header.setDefaultRenderer(new CustomHeaderRenderer());

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            int columnCount = table.getColumnCount();
            int columnWidth = 1550 / columnCount;
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth);
            }

            table.setRowHeight(30);
        } catch (Exception e) {
            e.printStackTrace();
        }

      
        nameSearchField = new JTextField();
        nameSearchField.setBounds(1100, 100, 200, 30);
        add(nameSearchField);

        JButton nameSearchButton = new JButton("Search by Name");
        nameSearchButton.setBackground(Color.RED);
        nameSearchButton.setForeground(Color.WHITE);
        nameSearchButton.setBounds(1350, 100, 150, 30);
        add(nameSearchButton);

        TableRowSorter<DefaultTableModel> nameSorter = new TableRowSorter<>(model);
        table.setRowSorter(nameSorter);

        nameSearchButton.addActionListener(e -> {
            String searchTerm = nameSearchField.getText().trim();
            if (searchTerm.length() == 0) {
                nameSorter.setRowFilter(null);
            } else {
                nameSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTerm, 2)); // 2 represents the third column (Name)
            }
        });
        backButton = new JButton("<");
        backButton.setBounds(10, 10, 80, 30);
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(new Color(230,230,250));
        backButton.setBorderPainted(false);
        add(backButton);


        backButton.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new AdminOptions(); 
            }
        });
        JButton viewLatestComplaintButton = new JButton("Recent Complaints (Last 24 Hours)");
        viewLatestComplaintButton.setBackground(Color.RED);
        viewLatestComplaintButton.setForeground(Color.WHITE);

        viewLatestComplaintButton.setBounds(50, 100, 250, 30);
        add(viewLatestComplaintButton);

        viewLatestComplaintButton.addActionListener(e -> {
            viewLatestComplaints();
        });
    }

    class CustomHeaderRenderer extends DefaultTableCellRenderer {

        public CustomHeaderRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
            setFont(new Font("Brush Script MT", Font.BOLD, 22));
            setBackground(new Color(255, 182, 193));
            setForeground(Color.BLUE);
            setBorder(BorderFactory.createEtchedBorder());
        }
    }

  private void viewLatestComplaints() {
      int x=200;
      int y=200;
    JDialog recentComplaintsDialog = new JDialog(this, "Recent Complaints (Last 24 Hours)", true);
    recentComplaintsDialog.setSize(200, 800); // Start with a small width
    recentComplaintsDialog.setLocation(x,y);
    recentComplaintsDialog.setLayout(new BorderLayout());
   

  

    // Create the table
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, -1);

    Date yesterday = calendar.getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String formattedDate = dateFormat.format(yesterday);

    String query = "SELECT * FROM Complaints WHERE STR_TO_DATE(registration_datetime, '%Y-%m-%d %H:%i:%s') > STR_TO_DATE('" + formattedDate + "', '%Y-%m-%d %H:%i:%s')";

    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", "");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        DefaultTableModel latestComplaintModel = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
        JTable latestComplaintTable = new JTable(latestComplaintModel);
       
        JLabel heading = new JLabel("Recent Complaints (Last 24 Hours)");
        heading.setFont(new Font("serif", Font.BOLD, 22));
        heading.setForeground(Color.BLUE);

        // Add the table and heading to the dialog content
        JPanel dialogContent = new JPanel(new BorderLayout());
        dialogContent.add(heading, BorderLayout.NORTH);
        dialogContent.add(new JScrollPane(latestComplaintTable), BorderLayout.CENTER);

        recentComplaintsDialog.add(dialogContent, BorderLayout.CENTER);

    } catch (Exception ex) {
        ex.printStackTrace();
    }

   

    // Add animation to the dialog
    Timer dialogTimer = new Timer(20, new ActionListener() {
        int width = 200;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (width < 1000) {
                width += 20;
            } else {
                width = 1000;
                ((Timer) e.getSource()).stop(); // Stop the animation after reaching the desired width
            }
            recentComplaintsDialog.setSize(width, 600);
        }
    });

    dialogTimer.start();

    recentComplaintsDialog.setVisible(true);
}



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RequestPage();
        });
    }
}
