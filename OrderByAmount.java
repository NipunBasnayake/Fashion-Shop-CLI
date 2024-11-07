import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

class OrderByAmount extends JFrame {
    private JButton btnBack;

    OrderByAmount(List ordersCollection) {
        setSize(500, 550);
        setTitle("Order By Amount");
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
                ViewReportsWindow viewReportsWindow = new ViewReportsWindow(ordersCollection);
                viewReportsWindow.setVisible(true);
                dispose();
            }
        });

        String[] columns = { "Order ID", "Customer ID", "Size", "Quantity", "Amount", "Status" };
        DefaultTableModel table = new DefaultTableModel(columns, 0);

        Order[] cusArray = ordersCollection.ordersByAmount();
        for (Order order : cusArray) {
            if (order != null) {
                Object[] rowData = { order.getOrderId(), order.getCustomerID(), order.getSize(), order.getQuantity(),
                        order.getAmount(), order.getOrderStatus() };
                table.addRow(rowData);
            }

        }
        JTable cusTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(cusTable);
        scrollPane.setBounds(20, 80, 440, 400);
        add(scrollPane);
    }
}
