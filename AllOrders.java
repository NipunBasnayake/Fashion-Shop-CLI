import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

class AllOrders extends JFrame {
    private JButton btnBack;

    AllOrders() {
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
                new ViewReportsWindow().setVisible(true);
                dispose();
            }
        });

        String[] colNames = { "Order ID", "Customer ID", "Size", "Quantity", "Amount", "Status" };
        DefaultTableModel table = new DefaultTableModel(colNames, 0);

        try {
            List orderList = OrderController.viewCustomers();
            if(orderList!=null){
                Order[] copyOrderArray = orderList.toArray();

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
            }else{
                JOptionPane.showMessageDialog(null, "Customers are not added to the system");
            }
        } catch (Exception e) {}

        JTable cusTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(cusTable);
        scrollPane.setBounds(20, 80, 440, 400);
        add(scrollPane);
    }
}
