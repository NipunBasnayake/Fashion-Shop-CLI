import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

class AllOrders extends JFrame {
    private JButton btnBack;

    AllOrders(List ordersCollection) {
        setSize(500, 550);
        setTitle("All Orders");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBackground(new Color(255, 102, 102));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(20, 20, 100, 35);
        add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new ViewReportsWindow(ordersCollection).setVisible(true);
                dispose();
            }
        });

        String[] colNames = { "Order ID", "Customer ID", "Size", "Quantity", "Amount", "Status" };
        DefaultTableModel table = new DefaultTableModel(colNames, 0);

        List orderList = new List(100, 0.25);

        try {
            Scanner input = new Scanner(new File("OrdersDoc.txt"));
            while (input.hasNext()) {
                String line = input.nextLine();
                String[] rowData = line.split(",");
                Order newOrder = new Order(rowData[0], rowData[1], Integer.parseInt(rowData[2]),
                        Double.parseDouble(rowData[3]), rowData[4], rowData[5]);

                orderList.add(newOrder);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error reading orders file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        Order[] copyOrderArray = orderList.getOrderArray();

        for (Order order : copyOrderArray) {
            if (order != null) {
                Object[] rowData = {
                        order.getOrderId(),
                        order.getCustomerID(),
                        order.getSize(),
                        order.getQuantity(),
                        order.getAmount(),
                        order.getOrderStatus()
                };
                table.addRow(rowData);
            }
        }

        JTable cusTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(cusTable);
        scrollPane.setBounds(20, 80, 440, 400);
        add(scrollPane);
    }
}
