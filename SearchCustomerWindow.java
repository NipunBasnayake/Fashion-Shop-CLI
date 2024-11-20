import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

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

    SearchCustomerWindow(List ordersCollection) {
        setSize(500, 550);
        setTitle("Fashion Shop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBackground(new Color(255, 102, 102));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(20, 20, 100, 35);
        add(btnBack);
        btnBack.addActionListener(evt -> dispose());

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
        btnSearch.setBackground(new Color(4, 203, 201));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Arial", Font.BOLD, 16));
        add(btnSearch);

        String[] columnNames = { "Size", "QTY", "Amount" };
        model = new DefaultTableModel(columnNames, 0);
        
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String customerId = txtCusID.getText().trim();
                List searchCustomerList = new List();
        
                model.setRowCount(0);
        
                try (Scanner input = new Scanner(new File("OrdersDoc.txt"))) {
                    while (input.hasNext()) {
                        String line = input.nextLine();
                        String[] rowData = line.split(",");
        
                        Order newOrder = new Order(rowData[0], rowData[1], Integer.parseInt(rowData[2]),
                                Double.parseDouble(rowData[3]), rowData[4], rowData[5]);
        
                        if (newOrder.getCustomerID().equals(customerId)) {
                            searchCustomerList.add(newOrder);
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(SearchCustomerWindow.this,
                            "Error reading orders file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
        
                String[] allSizes = { "XS", "S", "M", "L", "XL", "XXL" };
                double totalAmount = 0;
        
                for (String size : allSizes) {
                    int totalQtyForSize = 0;
                    double totalAmountForSize = 0;
        
                    for (int i = 0; i < searchCustomerList.size(); i++) {
                        Order foundOrder = searchCustomerList.get(i);
                        if (foundOrder.getSize().equals(size)) {
                            totalQtyForSize += foundOrder.getQuantity();
                            totalAmountForSize += foundOrder.getAmount();
                        }
                    }
        
                    if (totalQtyForSize > 0) {
                        model.addRow(new Object[] { size, totalQtyForSize, String.format("%.2f", totalAmountForSize) });
                        totalAmount += totalAmountForSize;
                    } else {
                        model.addRow(new Object[] { size, 0, "0.00" });
                    }
                }
                totalLabel.setText(String.format("Total: %.2f", totalAmount));
        
                if (searchCustomerList.size() == 0) {
                    JOptionPane.showMessageDialog(SearchCustomerWindow.this,
                            "Order not found for Customer ID: " + customerId, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        table = new JTable(model);
        table.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(20, 140, 440, 300);
        add(tableScrollPane);

        totalLabel = new JLabel("Total: 0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setBounds(20, 450, 200, 30);
        add(totalLabel);
    }
}
