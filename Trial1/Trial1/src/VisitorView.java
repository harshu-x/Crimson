

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisitorView extends JFrame {
    JTable table;
    DefaultTableModel model;
    JLabel heading;
    JButton backButton;

    VisitorView() {
        getContentPane().setBackground(new Color(230, 230, 250));
        setLayout(null);
        setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        
        
        
        heading = new JLabel("VISITORS  DATA");
        heading.setBounds(500, -10, 800, 200);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel shadow = new JLabel("VISITORS DATA");
        shadow.setBounds(heading.getX()+4, heading.getY()+4, heading.getWidth(), heading.getHeight());
        shadow.setFont(new Font("serif", Font.PLAIN, 60));
        shadow.setForeground(new Color(255,182,193));
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
        scrollPane.setBounds(0, 150, Toolkit.getDefaultToolkit().getScreenSize().width, 930);
        add(scrollPane);

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredScrollableViewportSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 500)); // Set the preferred size
        scrollPane.setViewportView(table);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", "");

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM visitation");
            model = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
            table.setModel(model);

            JTableHeader header = table.getTableHeader();
            header.setDefaultRenderer(new CustomHeaderRenderer());

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(i).setPreferredWidth(Toolkit.getDefaultToolkit().getScreenSize().width / table.getColumnCount()); // Adjust column width
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

        JTextField searchField = new JTextField(" "); // Add placeholder text
        searchField.setBounds(1100, 100, 200, 30);
        add(searchField);

        JButton searchButton = new JButton("Search By Criminal Name");
        searchButton.setBackground(Color.RED);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBounds(1320, 100, 180, 30);
        add(searchButton);

        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setBounds(1100, 150, 400, 400);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        add(resultTextArea);

        searchButton.addActionListener(e -> {
            String searchTerm = searchField.getText().trim();
            if (searchTerm.length() == 0) {
                resultTextArea.setText("Please enter a criminal name.");
            } else {
                String results = getVisitorsAndRelationship(searchTerm);
                resultTextArea.setText(results);
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
                new VisitorChoice().setVisible(true);
            }
        });
    }

    class CustomHeaderRenderer extends DefaultTableCellRenderer {
        public CustomHeaderRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
            setFont(new Font("Brush Script MT", Font.BOLD, 22));
            setBackground(new Color(255,182,193));
            setForeground(Color.BLUE);
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new VisitorView();
        });
    }

  
        private String getVisitorsAndRelationship(String searchTerm) {
        // Initialize the connection, statement, and result set
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Create a panel to display the results
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(0, 1));
        resultPanel.setBackground(new Color(230, 230, 250)); // Set background color

        // Label for displaying the criminal name
        JLabel nameLabel = new JLabel("Results for Criminal Name: " + searchTerm);
        nameLabel.setFont(new Font("serif", Font.PLAIN, 24));
        resultPanel.add(nameLabel);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp", "root", "");

            // SQL query to retrieve visitors, relationships, contact numbers, address, and date based on criminal name
            String sql = "SELECT visitor_name, relation, contact_number, location,visit_date  FROM visitation WHERE criminal_name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, searchTerm);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String visitorName = resultSet.getString("visitor_name");
                String relationship = resultSet.getString("relation");
                String contactNumber = resultSet.getString("contact_number");
                String address = resultSet.getString("location");
                String date = resultSet.getString("visit_date");

                JLabel resultLabel = new JLabel("Visitor: " + visitorName + ", Relationship: " + relationship
                        + ", Contact Number: " + contactNumber + ", Location: " + address + ", Date: " + date);
                resultPanel.add(resultLabel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Error: Unable to retrieve data from the database.");
            resultPanel.add(errorLabel);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Create a scroll pane for the result panel
        JScrollPane resultScrollPane = new JScrollPane(resultPanel);
        resultScrollPane.setPreferredSize(new Dimension(800, 400));

        // Show the result panel in a dialog window
        JOptionPane.showMessageDialog(this, resultScrollPane, "Search Results", JOptionPane.PLAIN_MESSAGE);

        return "";  // You can return an empty string or modify it as needed
    }
}
