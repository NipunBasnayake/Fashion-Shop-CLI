import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class DeleteOrderWindow extends JFrame {
    private JLabel lblCustID, lblSize, lblQTY, lblAmount, lblStatus;
    private JLabel lblGetCustID, lblGetSize, lblGetQTY, lblGetAmount, lblGetStatus;
    private JButton btnBack, btnSearch, btnDeleteOrder;
    private JLabel lblEnterID;
    private JTextField txtOrderID;
    private List orderList;

    DeleteOrderWindow(List ordersCollection) {

        this.orderList = ordersCollection;

        try {
            Scanner input = new Scanner(new File("OrdersDoc.txt"));
            while (input.hasNext()) {
                String line = input.nextLine();
                String[] rowData = line.split(",");
                Order newOrder = new Order(rowData[0], rowData[1], Integer.parseInt(rowData[2]),
                        Double.parseDouble(rowData[3]), rowData[4], rowData[5]);

                orderList.add(newOrder);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading orders file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        setSize(500, 550);
        setTitle("Delete Order");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                new HomeWindow(ordersCollection).setVisible(true);
                dispose();
            }
        });

        lblEnterID = new JLabel("Enter Order ID");
        lblEnterID.setFont(new Font("Arial", Font.BOLD, 16));
        lblEnterID.setBounds(20, 85, 150, 30);
        add(lblEnterID);

        txtOrderID = new JTextField(15);
        txtOrderID.setFont(new Font("Arial", Font.BOLD, 16));
        txtOrderID.setBounds(170, 85, 180, 30);
        add(txtOrderID);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(360, 85, 100, 30);
        add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boolean isFound = false;
                for (Order order : orderList.toArray()) {
                    if (txtOrderID.getText().equalsIgnoreCase(order.getOrderId())) {
                        lblGetSize.setText(order.getSize());
                        lblGetQTY.setText(String.valueOf(order.getQuantity()));
                        lblGetAmount.setText(String.valueOf(order.getAmount()));
                        lblGetCustID.setText(order.getCustomerID());
                        lblGetStatus.setText(order.getOrderStatus());
                        isFound = true;
                        break;
                    }
                }
                if (!isFound) {
                    JOptionPane.showMessageDialog(null, "Order not Found");
                }
            }
        });

        lblCustID = new JLabel("Customer ID:");
        lblCustID.setFont(new Font("Arial", Font.BOLD, 16));
        lblCustID.setBounds(20, 170, 150, 25);
        add(lblCustID);

        lblSize = new JLabel("Size:");
        lblSize.setFont(new Font("Arial", Font.BOLD, 16));
        lblSize.setBounds(20, 220, 150, 25);
        add(lblSize);

        lblQTY = new JLabel("Quantity:");
        lblQTY.setFont(new Font("Arial", Font.BOLD, 16));
        lblQTY.setBounds(20, 270, 150, 25);
        add(lblQTY);

        lblAmount = new JLabel("Amount:");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 16));
        lblAmount.setBounds(20, 320, 150, 25);
        add(lblAmount);

        lblStatus = new JLabel("Status:");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
        lblStatus.setBounds(20, 370, 150, 25);
        add(lblStatus);

        lblGetCustID = new JLabel("");
        lblGetCustID.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetCustID.setBounds(150, 170, 150, 25);
        add(lblGetCustID);

        lblGetSize = new JLabel("");
        lblGetSize.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetSize.setBounds(150, 220, 150, 25);
        add(lblGetSize);

        lblGetQTY = new JLabel("");
        lblGetQTY.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetQTY.setBounds(150, 270, 150, 25);
        add(lblGetQTY);

        lblGetAmount = new JLabel("");
        lblGetAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetAmount.setBounds(150, 320, 150, 25);
        add(lblGetAmount);

        lblGetStatus = new JLabel("");
        lblGetStatus.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetStatus.setBounds(150, 370, 150, 25);
        add(lblGetStatus);

        btnDeleteOrder = new JButton("Delete Order");
        btnDeleteOrder.setFont(new Font("Arial",Font.BOLD,12));
        btnDeleteOrder.setBackground(new Color(135,193,255));
        btnDeleteOrder.setForeground(Color.WHITE);
        btnDeleteOrder.setBounds(320,400,150,30);
        add(btnDeleteOrder);
        btnDeleteOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String id = txtOrderID.getText();
        
                List updatedOrderList = new List();
                try (Scanner input = new Scanner(new File("OrdersDoc.txt"))) {
                    while (input.hasNext()) {
                        String line = input.nextLine();
                        String[] rowData = line.split(",");
                        if (rowData[0].equalsIgnoreCase(id)) {
                            continue;
                        }
                        Order order = new Order(rowData[0], rowData[1], Integer.parseInt(rowData[2]),
                                Double.parseDouble(rowData[3]), rowData[4], rowData[5]);
                        updatedOrderList.add(order);
                    }
                    try (FileWriter fw = new FileWriter("OrdersDoc.txt")) {
                        for (Order order : updatedOrderList.toArray()) {
                            fw.write(order.toString() + "\n");
                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error deleting order: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
    }
}
