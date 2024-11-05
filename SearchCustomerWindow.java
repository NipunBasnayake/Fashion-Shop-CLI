import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SearchCustomerWindow extends JFrame {
    private JButton btnBack;
    private JLabel lblEnterID;
    private JTextField txtCusID;
    private JButton btnSearch;
    private JTable table;
    private DefaultTableModel model;
    private JLabel totalLabel;

    SearchCustomerWindow(OrdersCollection ordersCollection) {
        setSize(500, 550);
        setTitle("Fashion Shop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // ----------------- Back Button Panel -----------------
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBackground(new Color(255, 102, 102));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(20, 20, 100, 35);
        add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });

        // ----------------- Search Panel -----------------
        lblEnterID = new JLabel("Enter Customer ID");
        lblEnterID.setFont(new Font("Arial", Font.BOLD, 16));
        lblEnterID.setBounds(20, 85, 150, 30);
        add(lblEnterID);

        txtCusID = new JTextField(15);
        txtCusID.setFont(new Font("Arial", Font.BOLD, 16));
        txtCusID.setBounds(170, 85, 180, 30);
        add(txtCusID);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(360, 85, 100, 30);
        add(btnSearch);

        String[] columnNames = { "Size", "QTY", "Amount" };
        model = new DefaultTableModel(columnNames, 0);

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

        table = new JTable(model);
        table.setRowHeight(30);

        // Center-align cell content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(20, 140, 440, 350);

        add(tableScrollPane);

    }
}
