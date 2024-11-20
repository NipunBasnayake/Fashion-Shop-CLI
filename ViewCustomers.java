import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

class ViewCustomers extends JFrame {
    private JButton btnBack;

    ViewCustomers(List ordersCollection) {
        setSize(500, 550);
        setTitle("View Customers");
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
                dispose();
                new ViewReportsWindow(ordersCollection).setVisible(true);
            }
        });

        String[] columns = { "Customer ID", "QTY", "Amount" };
        DefaultTableModel table = new DefaultTableModel(columns, 0);

        List orderList = new List();

        try {
            Scanner input = new Scanner(new File("OrdersDoc.txt"));
            while (input.hasNextLine()) {
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

        Order[] uniqueOrders = new Order[orderList.toArray().length];
        int uniqueCustomerCount = 0;

        for (int i = 0; i < orderList.size(); i++) {
            Order order = (Order) orderList.get(i);
            String tpNumber = order.getCustomerID();
            int qty = order.getQuantity();
            double amount = order.getAmount();

            boolean isNewCustomer = true;
            int customerIndex = -1;

            for (int j = 0; j < uniqueCustomerCount; j++) {
                if (uniqueOrders[j].getCustomerID().equals(tpNumber)) {
                    uniqueOrders[j].setQuantity(qty);
                    uniqueOrders[j].setAmount(amount);
                    isNewCustomer = false;
                    break;
                }
            }

            if (isNewCustomer) {
                uniqueOrders[uniqueCustomerCount] = new Order();
                uniqueOrders[uniqueCustomerCount].setCustomerID(tpNumber);
                uniqueOrders[uniqueCustomerCount].setQuantity(qty);
                uniqueOrders[uniqueCustomerCount].setAmount(amount);
                uniqueCustomerCount++;
            }
        }

        for (int i = 0; i < uniqueCustomerCount; i++) {
            Order order = uniqueOrders[i];
            Object[] rowData = { order.getCustomerID(), order.getQuantity(), order.getAmount() };
            table.addRow(rowData);
        }

        JTable cusTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(cusTable);
        scrollPane.setBounds(20, 80, 440, 400);
        add(scrollPane);
    }
}
