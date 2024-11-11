import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SearchCustomerWindow extends JFrame {
    private JButton btnBack;
    private JLabel lblEnterID;
    private JTextField txtCusID;
    private JButton btnSearch;
    private JTable table;
    private DefaultTableModel model;
    private JLabel totalLabel;

    SearchCustomerWindow() {
        setSize(500, 600);
        setTitle("Fashion Shop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBackground(new Color(255, 102, 102));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(20, 20, 100, 35);
        add(btnBack);
        btnBack.addActionListener(evt -> dispose());

        lblEnterID = new JLabel("Enter Customer ID");
        lblEnterID.setFont(new Font("Arial", Font.BOLD, 16));
        lblEnterID.setBounds(20, 85, 150, 30);
        add(lblEnterID);

        txtCusID = new JTextField(15);
        txtCusID.setFont(new Font("Arial", Font.BOLD, 16));
        txtCusID.setBounds(170, 85, 180, 30);
        add(txtCusID);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(360, 85, 100, 30);
        add(btnSearch);

        String[] columnNames = {"Size", "QTY", "Amount"};
        model = new DefaultTableModel(columnNames, 0);
        
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String customerId = txtCusID.getText().trim();
                Order[] ordersCollection = new Order[100];
                int orderCount = 0;
        
                try (BufferedReader br = new BufferedReader(new FileReader("OrdersDoc.txt"))) {
                    String line;
                    System.out.println("Searching for Customer ID: " + customerId);
                    while ((line = br.readLine()) != null) {
                        String[] fields = line.split(",");
                        if (fields.length < 4) continue;
        
                        String fileCustomerId = fields[0].trim();
                        String size = fields[1].trim();
                        int quantity = Integer.parseInt(fields[2].trim());
                        double amount = Double.parseDouble(fields[3].trim());
        
                        System.out.println("Comparing entered ID '" + customerId + "' with file ID '" + fileCustomerId + "'");
                        
                        if (fileCustomerId.equals(customerId) && orderCount < ordersCollection.length) {
                            Order newOrder = new Order();
                            newOrder.setCustomerID(fileCustomerId);
                            newOrder.setSize(size);
                            newOrder.setQuantity(quantity);
                            newOrder.setAmount(amount);
                            ordersCollection[orderCount] = newOrder;
                            orderCount++;
                        }
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error reading OrdersDoc.txt file.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                model.setRowCount(0);
                String[] allSizes = {"XS", "S", "M", "L", "XL", "XXL"};
                double totalAmount = 0;
        
                for (String size : allSizes) {
                    boolean sizeFound = false;
                    for (int i = 0; i < orderCount; i++) {
                        Order order = ordersCollection[i];
                        if (order != null && size.equals(order.getSize())) {
                            Object[] rowData = {size, order.getQuantity(), order.getAmount()};
                            model.addRow(rowData);
                            totalAmount += order.getAmount();
                            sizeFound = true;
                            break;
                        }
                    }
                    if (!sizeFound) {
                        Object[] rowData = {size, 0, "0.00"};
                        model.addRow(rowData);
                    }
                }
        
                totalLabel.setText(String.format("Total: %.2f", totalAmount));
        
                if (orderCount == 0) {
                    JOptionPane.showMessageDialog(SearchCustomerWindow.this,
                            "Order not found for Customer ID: " + customerId, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        table = new JTable(model);
        table.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(20, 140, 440, 350);
        add(tableScrollPane);

        totalLabel = new JLabel("Total: 0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setBounds(20, 500, 200, 30);
        add(totalLabel);
    }
}
