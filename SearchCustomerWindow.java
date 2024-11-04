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
                String customerId = txtCusID.getText();
                Order[] foundOrders = ordersCollection.searchCustomerID(customerId);

                model.setRowCount(0);

                String[] allSizes = { "XS", "S", "M", "L", "XL", "XXL" };
                double totalAmount = 0;

                for (String size : allSizes) {
                    boolean sizeFound = false;

                    for (Order foundOrder : foundOrders) {
                        if (foundOrder.getSize().equals(size)) {
                            Object[] rowData = { size, foundOrder.getQuantity(), foundOrder.getAmount() };
                            model.addRow(rowData);
                            totalAmount += foundOrder.getAmount();
                            sizeFound = true;
                            break;
                        }
                    }
                    if (!sizeFound) {
                        Object[] rowData = { size, 0, "0.00" };
                        model.addRow(rowData);
                    }
                }
                totalLabel.setText(String.format("Total: %.2f", totalAmount));

                if (foundOrders.length == 0) {
                    JOptionPane.showMessageDialog(SearchCustomerWindow.this,
                            "Order not found for Customer ID: " + customerId, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        searchPanel.add(lblEnterID);
        searchPanel.add(txtCusID);
        searchPanel.add(btnSearch);

        // ----------------- Table Panel -----------------
        String[] columnNames = { "Size", "QTY", "Amount" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setRowHeight(30); // Set row height

        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(50); // "Size" column width
        table.getColumnModel().getColumn(1).setPreferredWidth(50); // "QTY" column width
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // "Amount" column width

        // Add table to a scroll pane
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(25, 25, 450, 500); // Set position and size of the scroll pane

        // Add the scroll pane containing the table to the frame or panel
        add(tableScrollPane);

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
