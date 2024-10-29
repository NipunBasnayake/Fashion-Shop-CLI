import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

class SearchCustomerWindow extends JFrame {
    private JLabel lblTitle;
    private JLabel lblCustomerId;
    private JTextField txtCustomerId;
    private JButton btnSearchCustomer;
    private JTable tblSearchCustomerResults;

    SearchCustomerWindow() {
        setSize(700, 450);
        setTitle("Search Customer | Fashion Shop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        lblTitle = new JLabel("SEARCH CUSTOMER");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));  // Adjusted spacing here
        add("North", lblTitle);

        // ----------------- Search Inputs --------------------

        lblCustomerId = new JLabel("Customer Id:");
        lblCustomerId.setFont(new Font("Arial", Font.BOLD, 16));

        txtCustomerId = new JTextField(20);
        txtCustomerId.setFont(new Font("Arial", Font.PLAIN, 16));
        txtCustomerId.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Reduced padding

        btnSearchCustomer = new JButton("Search Customer");
        btnSearchCustomer.setFont(new Font("Arial", Font.BOLD, 16));
        btnSearchCustomer.setPreferredSize(new Dimension(170, 35)); // Adjusted button height

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        inputPanel.add(lblCustomerId);
        inputPanel.add(txtCustomerId);
        inputPanel.add(btnSearchCustomer);

        // ----------------- Table --------------------

        String[] columnNames = {"Size", "Qty", "Amount"};
        Object[][] data = {};

        tblSearchCustomerResults = new JTable(data, columnNames);
        tblSearchCustomerResults.setFont(new Font("Arial", Font.PLAIN, 14));
        tblSearchCustomerResults.setRowHeight(25);

        JTableHeader tableHeader = tblSearchCustomerResults.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        tableHeader.setBackground(new Color(70, 130, 180));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(100, 30));

        JScrollPane scrollPane = new JScrollPane(tblSearchCustomerResults);
        scrollPane.setPreferredSize(new Dimension(650, 150)); 

        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        tablePanel.add(scrollPane);

        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 0, 5)); 
        mainPanel.add(inputPanel);
        mainPanel.add(tablePanel);

        add("Center", mainPanel);
        setVisible(true);
    }
}
