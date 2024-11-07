import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
class AllOrders extends JFrame{
    private JButton btnBack;

    AllOrders(OrdersCollection ordersCollection){
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
                ViewReportsWindow viewReportsWindow = new ViewReportsWindow(ordersCollection);
                viewReportsWindow.setVisible(true);
                dispose();
            }
        });

        String[] colNames = {"Order ID","Customer ID","Size","Quantity","Amount","Status"};
        DefaultTableModel table = new DefaultTableModel(colNames,0);
        Order[] copyOrderArray = ordersCollection.getOrderArray();

        for (int i = copyOrderArray.length - 1; i >= 0; i--) {
            Object[] rowData = {
                copyOrderArray[i].getOrderId(),
                copyOrderArray[i].getCustomerID(),
                copyOrderArray[i].getSize(),
                copyOrderArray[i].getQuantity(),
                copyOrderArray[i].getAmount(),
                copyOrderArray[i].getOrderStatus()
            };
            table.addRow(rowData);
        }

        JTable cusTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(cusTable);
        scrollPane.setBounds(20, 80, 440, 400);
        add(scrollPane);
    }
}

