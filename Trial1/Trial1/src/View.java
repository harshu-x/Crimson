import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

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

        heading = new JLabel("Criminal Data Repository");
        heading.setBounds(300, -10, 800, 200);
        heading.setFont(new Font("Serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLUE);
        add(heading);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 150, 1920, 930);
        add(scrollPane);

        model = new DefaultTableModel();
        table = new JTable(model);

        scrollPane.setViewportView(table);
        loadTableData();

        searchField = new JTextField();
        searchField.setBounds(1100, 100, 200, 30);
        add(searchField);

        JButton searchButton = new JButton("Search By Name");
        searchButton.setBounds(1310, 100, 180, 30);
        searchButton.setBackground(Color.RED);
        searchButton.setForeground(Color.WHITE);
        add(searchButton);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        searchButton.addActionListener(e -> {
            String searchTerm = searchField.getText().trim();
            if (searchTerm.length() == 0) {
                sorter.setRowFilter(null);
            } else {
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
            dispose();
            new AdminOptions();
        });

        setVisible(true);
    }

    private void loadTableData() {
        String url = "jdbc:mysql://localhost:3306/login_erp"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = ""; // Replace with your database password
        String query = "SELECT * FROM adding"; // Replace with your table name

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Set up table columns
            model.setRowCount(0); // Clear previous data
            model.setColumnCount(0);
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rsmd.getColumnName(i));
            }

            // Populate table rows
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(View::new);
    }
}
