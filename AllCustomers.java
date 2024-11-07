import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AllCustomers extends JFrame {
    private JButton btnBack;

    AllCustomers(OrdersCollection ordersCollection) {
        setSize(500, 550);
        setTitle("All Customers");
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

        String[] columns = {"Customer ID", "XS", "S", "M", "L", "XL", "XXL", "Amount"};
        DefaultTableModel dtm = new DefaultTableModel(columns, 0);

        Order[] allCustomers = ordersCollection.allCustomers();
        for (Order customer : allCustomers) {
            if (customer != null && customer.getCustomerID() != null) {
                Object[] rowData = {
                    customer.getCustomerID(),
                    customer.getXtraSmall(),
                    customer.getSmall(),
                    customer.getMediumSize(),
                    customer.getLarge(),
                    customer.getXtraLarge(),
                    customer.getXtraXl(),
                    customer.getAmount()
                };
                dtm.addRow(rowData);
            }
        }

        JTable cusTable = new JTable(dtm);
        JScrollPane scrollPane = new JScrollPane(cusTable);
        scrollPane.setBounds(20, 80, 440, 400);
        add(scrollPane);
    }
}
