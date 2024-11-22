import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class CategorazedByAmount extends JFrame {
    private JButton btnBack;

    CategorazedByAmount() {
        setSize(500, 550);
        setTitle("Categorized By Amount");
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

        String[] columns = { "Size", "QTY", "Amount" };
        DefaultTableModel table = new DefaultTableModel(columns, 0);

        try {
            List orderList = OrderController.viewCustomers();
            if (orderList!=null) {
                Order[] sortedArray = sortByAmount(orderList);
                for (int i = 0; i < sortedArray.length; i++) {
                    Object[] rowData = { sortedArray[i].getSize(), sortedArray[i].getQuantity(), sortedArray[i].getAmount() };
                    table.addRow(rowData);
                }
            }else{

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error reading orders file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        JTable custTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(custTable);
        scrollPane.setBounds(20, 80, 440, 400);
        add(scrollPane);
    }

    public Order[] sortByAmount(List orderList) {
        Order[] orderArray = orderList.toArray();
        Order[] items = new Order[orderArray.length];
        int uniqueSizesCount = 0;

        for (Order order : orderArray) {
            if (order == null) {
                continue;
            }

            String size = order.getSize();
            int qty = order.getQuantity();
            double amount = order.getAmount();

            boolean isNewSize = true;
            int sizeIndex = -1;

            for (int i = 0; i < uniqueSizesCount; i++) {
                if (size != null && items[i] != null && items[i].getSize().equals(size)) {
                    isNewSize = false;
                    sizeIndex = i;
                    break;
                }
            }

            if (isNewSize) {
                Order newItem = new Order();
                newItem.setSize(size);
                newItem.setQuantity(qty);
                newItem.setAmount(amount);
                items[uniqueSizesCount] = newItem;
                uniqueSizesCount++;
            } else {
                items[sizeIndex].setQuantity(items[sizeIndex].getQuantity() + qty);
                items[sizeIndex].setAmount(items[sizeIndex].getAmount() + amount);
            }
        }

        for (int i = 0; i < uniqueSizesCount - 1; i++) {
            for (int j = 0; j < uniqueSizesCount - i - 1; j++) {
                if (items[j].getAmount() < items[j + 1].getAmount()) {
                    Order temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }

        Order[] result = new Order[uniqueSizesCount];
        System.arraycopy(items, 0, result, 0, uniqueSizesCount);
        return result;
    }
}
