import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

class ViewCustomers extends JFrame {
    private JButton btnBack;

    ViewCustomers(OrdersCollection ordersCollection) {
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

        Order[] orders = ordersCollection.viewCustomers();

        for (Order order : orders) {
            Object[] rowData = { order.getCustomerID(), order.getQuantity(), order.getAmount() };
            table.addRow(rowData);
        }

        JTable cusTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(cusTable);
        scrollPane.setBounds(20, 80, 440, 400);
        add(scrollPane);
    }
}
