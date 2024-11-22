import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

class ViewCustomers extends JFrame {
    private JButton btnBack;

    ViewCustomers() {
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
                new ViewReportsWindow().setVisible(true);
            }
        });

        String[] columns = { "Customer ID", "QTY", "Amount" };
        DefaultTableModel table = new DefaultTableModel(columns, 0);
        
        try {
            List orderList = OrderController.viewCustomers();
            if (orderList != null) {
                Order[] uniqueOrders = new Order[orderList.toArray().length];
                int uniqueCustomerCount = 0;
        
                for (int i = 0; i < orderList.size(); i++) {
                    Order order = (Order) orderList.get(i);
                    String tpNumber = order.getCustomerID();
                    int qty = order.getQuantity();
                    double amount = order.getAmount();
        
                    boolean isNewCustomer = true;
        
                    for (int j = 0; j < uniqueCustomerCount; j++) {
                        if (uniqueOrders[j].getCustomerID().equals(tpNumber)) {
                            uniqueOrders[j].setQuantity(uniqueOrders[j].getQuantity() + qty);
                            uniqueOrders[j].setAmount(uniqueOrders[j].getAmount() + amount);
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
            } else {
                JOptionPane.showMessageDialog(null, "Customers are not added to the system");
            }
        } catch (Exception e) {}
        
        JTable cusTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(cusTable);
        scrollPane.setBounds(20, 80, 440, 400);
        add(scrollPane);
    }
}
