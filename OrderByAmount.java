import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

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



        Order[] cusArray = ordersByAmount(orderList);
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

    public Order[] ordersByAmount(List orderList) {
        
        Order[] orderArray = orderList.getOrderArray();
        Order[] sortByAmountArray = new Order[orderArray.length];
        for (int i = 0; i < orderArray.length; i++) {
            sortByAmountArray[i] = orderArray[i];
        }

        for (int i = orderArray.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (sortByAmountArray[j] != null && sortByAmountArray[j + 1] != null &&
                        sortByAmountArray[j].getAmount() < sortByAmountArray[j + 1].getAmount()) {

                    Order temp = sortByAmountArray[j];
                    sortByAmountArray[j] = sortByAmountArray[j + 1];
                    sortByAmountArray[j + 1] = temp;
                }
            }
        }
        return sortByAmountArray;
    }
}
