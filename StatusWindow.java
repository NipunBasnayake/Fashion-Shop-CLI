import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class StatusWindow extends JFrame {
    private JLabel lblCustID, lblSize, lblQTY, lblAmount, lblStatus;
    private JLabel lblGetCustID, lblGetSize, lblGetQTY, lblGetAmount, lblGetStatus;
    private JButton btnBack, btnSearch, btnChangeStatus;
    private JLabel lblEnterID;
    private JTextField txtOrderID;
    private List orderList; // This is assumed to be a custom List class

    StatusWindow(List ordersCollection) {
        // Initialize orderList
        this.orderList = ordersCollection; // Ensure this is initialized

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
        setTitle("Change Order Status");
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
                new HomeWindow(orderList).setVisible(true);
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
                for (Order order : orderList.getOrderArray()) {
                    if (txtOrderID.getText().equalsIgnoreCase(order.getOrderId())) {
                        lblGetSize.setText(order.getSize());
                        lblGetQTY.setText(String.valueOf(order.getQuantity()));
                        lblGetAmount.setText(String.valueOf(order.getAmount()));
                        lblGetCustID.setText(order.getCustomerID());
                        lblGetStatus.setText(order.getOrderStatus());
                        isFound = true;
                        break; // Stop loop when found
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

        btnChangeStatus = new JButton("CHANGE STATUS");
        btnChangeStatus.setFont(new Font("Arial", Font.BOLD, 12));
        btnChangeStatus.setBackground(new Color(135, 193, 255));
        btnChangeStatus.setForeground(Color.WHITE);
        btnChangeStatus.setBounds(320, 400, 150, 30);
        add(btnChangeStatus);

        btnChangeStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int status = changeOrderStatus(txtOrderID.getText());
                if (status == 0) {
                    Icon qIcon = UIManager.getIcon("JOptionPane.questionIcon");
                    Object[] buttons = { "Delivering", "Delivered" };

                    int selection = JOptionPane.showOptionDialog(
                            null,
                            "Please Select the Status",
                            "Status",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            qIcon,
                            buttons,
                            buttons[0]);
                    switch (selection) {
                        case 0:
                            setOrderStatus(1, (txtOrderID.getText()));
                            break;
                        case 1:
                            setOrderStatus(2, (txtOrderID.getText()));
                            break;
                        default:
                            System.out.println("default");
                            break;
                    }
                } else if (status == 1) {
                    Icon qIcon = UIManager.getIcon("JOptionPane.questionIcon");
                    Object[] button = { "Delivered" };

                    int selection = JOptionPane.showOptionDialog(
                            null,
                            "Please select the Status",
                            "Status",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            qIcon,
                            button,
                            button[0]);
                    switch (selection) {
                        case 0:
                            setOrderStatus(2, (txtOrderID.getText()));
                            break;
                        default:
                            System.out.println("Default");
                            break;
                    }
                } else if (status == 2) {
                    JOptionPane.showMessageDialog(null, "Can't change this order status...order already delivered...!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                writeDataToFile();
            }
        });
    }

    public void setOrderStatus(int status, String orderId) {
        Order[] orderArray = orderList.getOrderArray();
        for (int i = 0; i < orderArray.length; i++) {
            if (orderArray[i].getOrderId() != null && orderId.equals(orderArray[i].getOrderId())) {
                if (status == 1) {
                    orderArray[i].setOrderStatus("Delivering");
                    break;
                } else if (status == 2) {
                    orderArray[i].setOrderStatus("Delivered");
                    break;
                }
            }
        }
    }

    public int changeOrderStatus(String id) {
        Order[] orderArray = orderList.getOrderArray();
        for (int i = 0; i < orderArray.length; i++) {
            if (orderArray[i].getOrderId() != null && orderArray[i].getOrderId().equalsIgnoreCase(id)) {
                if (orderArray[i].getOrderStatus().equals("Processing")) {
                    return 0;
                } else if (orderArray[i].getOrderStatus().equals("Delivering")) {
                    return 1;
                } else if (orderArray[i].getOrderStatus().equals("Delivered")) {
                    return 2;
                }
            }
        }
        return -1;
    }

    public void writeDataToFile() {
        try (FileWriter writer = new FileWriter("OrdersDoc.txt", false);
                BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            for (Order order : orderList.getOrderArray()) {
                if (order != null) {
                    bufferedWriter.write(order.getOrderId() + "," + order.getSize() + "," + order.getQuantity() + ","
                            + order.getAmount() + "," + order.getCustomerID() + "," + order.getOrderStatus());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {

        }
    }

}
