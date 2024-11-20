import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

class AllCustomers extends JFrame {
    private JButton btnBack;

    AllCustomers(List ordersCollection) {
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

        List orderList = new List();

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

        String[] columns = { "Customer ID", "XS", "S", "M", "L", "XL", "XXL", "Amount" };
        DefaultTableModel dtm = new DefaultTableModel(columns, 0);

        Order[] allCustomers = allCustomers(orderList);

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

    public Order[] allCustomers(List orderList) {

        Order[] orderArray = orderList.toArray();

        Order[] orders = new Order[orderArray.length];
        boolean[] processed = new boolean[orderArray.length];
        int count = 0;

        for (int i = 0; i < orderArray.length; i++) {
            if (processed[i] || orderArray[i] == null) {
                continue;
            }

            String customerId = orderArray[i].getCustomerID();
            if (customerId == null) {
                continue;
            }

            Order aggregatedOrder = new Order();
            aggregatedOrder.setCustomerID(customerId);
            double totalAmount = 0;
            int xsQty = 0, sQty = 0, mQty = 0, lQty = 0, xlQty = 0, xxlQty = 0;

            for (int j = i; j < orderArray.length; j++) {
                if (orderArray[j] != null && customerId.equals(orderArray[j].getCustomerID())) {
                    String size = orderArray[j].getSize();
                    if (size != null) {
                        switch (size) {
                            case "XS":
                                xsQty += orderArray[j].getQuantity();
                                break;
                            case "S":
                                sQty += orderArray[j].getQuantity();
                                break;
                            case "M":
                                mQty += orderArray[j].getQuantity();
                                break;
                            case "L":
                                lQty += orderArray[j].getQuantity();
                                break;
                            case "XL":
                                xlQty += orderArray[j].getQuantity();
                                break;
                            case "XXL":
                                xxlQty += orderArray[j].getQuantity();
                                break;
                        }
                    }
                    totalAmount += orderArray[j].getAmount();
                    processed[j] = true;
                }
            }

            aggregatedOrder.setXtraSmall(xsQty);
            aggregatedOrder.setSmall(sQty);
            aggregatedOrder.setMediumSize(mQty);
            aggregatedOrder.setLarge(lQty);
            aggregatedOrder.setXtraLarge(xlQty);
            aggregatedOrder.setXtraXl(xxlQty);
            aggregatedOrder.setAmount(totalAmount);

            orders[count++] = aggregatedOrder;
        }

        Order[] result = new Order[count];
        System.arraycopy(orders, 0, result, 0, count);
        return result;
    }
}
