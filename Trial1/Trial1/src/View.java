import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import net.proteanit.sql.*;
import org.apache.commons.dbutils.DbUtils;

public class View extends JFrame {
    JTable table;
    DefaultTableModel model;
    JLabel heading;
    JButton backButton;
    JTextField searchField;

    public View() {
        getContentPane().setBackground(new Color(230, 230, 250));
        setLayout(null);
        setBounds(0, 0, 1920, 1080);
        setVisible(true);

        heading = new JLabel("Criminal Data Repository");
        heading.setBounds(300, -10, 800, 200);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLUE);
        add(heading);

        // Add a shadow label
        JLabel shadow = new JLabel("Criminal Data Repository");
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
        scrollPane.setBounds(0, 150, 1920, 930);
        add(scrollPane);

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Set auto-resize mode to OFF

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", ""); // Provide your correct username and password

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT  * FROM adding");
            model = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
            table.setModel(model);

            JTableHeader header = table.getTableHeader();
            header.setDefaultRenderer(new CustomHeaderRenderer());

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer); // Center align all cells

            // Calculate the preferred width for each column based on your total table width
            int totalWidth = 1920; // Total width of your JFrame
            int columnCount = table.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                int columnWidth = totalWidth / columnCount; // Equal distribution of width
                table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth);
            }

            table.setRowHeight(30);
            Color skyBlue = new Color(135, 206, 235);
            for (int row = 0; row < table.getRowCount(); row += 2) {
                table.addRowSelectionInterval(row, row);
                table.setSelectionBackground(skyBlue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        scrollPane.setViewportView(table);

        searchField = new JTextField();
        searchField.setBounds(1100, 100, 200, 30);
        add(searchField);

        JButton searchButton = new JButton("Search By Name");
        searchButton.setBounds(1310, 100, 180, 30);
        searchButton.setBackground(Color.red);
        searchButton.setForeground(Color.WHITE);
        add(searchButton);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        searchButton.addActionListener(e -> {
            String searchTerm = searchField.getText().trim();
            if (searchTerm.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                // Filter based on the first column (Name)
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTerm, 0));
            }
        });

        backButton = new JButton("<");
        backButton.setBounds(10, 10, 80, 30);
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(new Color(230, 230, 250));
        backButton.setBorderPainted(false);
        add(backButton);

        backButton.addActionListener(e -> {
            dispose(); // Close the current view
            new AdminOptions(); // Open the AdminOptions page
        });
    }

    class CustomHeaderRenderer extends DefaultTableCellRenderer {
        public CustomHeaderRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
            setFont(new Font("Brush Script MT", Font.BOLD, 22));
            setBackground(Color.BLACK);
            setForeground(Color.BLUE);
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new View();
        });
    }
}
