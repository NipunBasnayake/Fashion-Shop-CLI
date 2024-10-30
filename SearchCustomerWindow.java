import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class SearchCustomerWindow extends JFrame {
    private JButton btnBack;
    private JLabel lblEnterID;
    private JTextField txtCusID;
    private JButton btnSearch;
    private JTable table;
    private DefaultTableModel model;
    private JLabel totalLabel;
    private OrdersCollection ordersCollection; 

    SearchCustomerWindow(OrdersCollection ordersCollection) {
        this.ordersCollection = ordersCollection;
        setSize(500, 600);
        setTitle("Fashion Shop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ----------------- Back Button Panel -----------------
        JPanel pnlBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setPreferredSize(new Dimension(100, 35));
        btnBack.setBackground(new Color(255, 102, 102));
        btnBack.setForeground(Color.WHITE);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });

        pnlBack.add(btnBack);
        add("North", pnlBack);

        // ----------------- Search Panel -----------------
        JPanel searchPanel = new JPanel(new FlowLayout());
        lblEnterID = new JLabel("Enter Customer ID :");
        txtCusID = new JTextField(15);
        btnSearch = new JButton("Search");

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String customerId = txtCusID.getText().trim();
                Order[] foundOrders = ordersCollection.searchCustomerID(customerId);
        
                model.setRowCount(0);
        
                if (foundOrders.length > 0) {
                    double totalAmount = 0;
                    for (Order foundOrder : foundOrders) {
                        Object[] rowData = {foundOrder.getSize(), foundOrder.getQuantity(), foundOrder.getAmount()};
                        model.addRow(rowData); 
                        totalAmount += foundOrder.getAmount();
                    }
                    totalLabel.setText(String.format("Total: %.2f", totalAmount));
                } else {
                    JOptionPane.showMessageDialog(SearchCustomerWindow.this, "Order not found for Customer ID: " + customerId, "Error", JOptionPane.ERROR_MESSAGE);
                    totalLabel.setText("Total: 0.00");
                }
            }
        });
        
        searchPanel.add(lblEnterID);
        searchPanel.add(txtCusID);
        searchPanel.add(btnSearch);

        // ----------------- Table Panel -----------------
        String[] columnNames = {"Size", "QTY", "Amount"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // ----------------- Center Panel -----------------
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // ----------------- Total Panel -----------------
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalLabel = new JLabel("");
        bottomPanel.add(totalLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
