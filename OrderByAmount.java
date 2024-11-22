import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class OrderByAmount extends JFrame {
    private JButton btnBack;

    OrderByAmount() {
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
                ViewReportsWindow viewReportsWindow = new ViewReportsWindow();
                viewReportsWindow.setVisible(true);
                dispose();
            }
        });

        String[] columns = { "Order ID", "Customer ID", "Size", "Quantity", "Amount", "Status" };
        DefaultTableModel table = new DefaultTableModel(columns, 0);

        try {
            List orderList = OrderController.viewCustomers();
            if (orderList!=null) {
                Order[] cusArray = ordersByAmount(orderList);
                for (Order order : cusArray) {
                    if (order != null) {
                        Object[] rowData = { order.getOrderId(), order.getCustomerID(), order.getSize(), order.getQuantity(),
                                order.getAmount(), order.getOrderStatus() };
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

    public Order[] ordersByAmount(List orderList) {
        
        Order[] orderArray = orderList.toArray();
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
