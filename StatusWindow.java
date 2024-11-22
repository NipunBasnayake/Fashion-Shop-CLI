import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

class StatusWindow extends JFrame {
    private JLabel lblCustID, lblSize, lblQTY, lblAmount, lblStatus;
    private JLabel lblGetCustID, lblGetSize, lblGetQTY, lblGetAmount, lblGetStatus;
    private JButton btnBack, btnSearch, btnChangeStatus;
    private JLabel lblEnterID;
    private JTextField txtOrderID;
    static Order searchedResult;

    StatusWindow() {
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
                new HomeWindow().setVisible(true);
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
                try {
                    searchedResult = OrderController.searchOrderId(txtOrderID.getText());
                    if (searchedResult != null) {
                        lblGetSize.setText(searchedResult.getSize());
                        lblGetQTY.setText(String.valueOf(searchedResult.getQuantity()));
                        lblGetAmount.setText(String.valueOf(searchedResult.getAmount()));
                        lblGetCustID.setText(searchedResult.getCustomerID());
                        lblGetStatus.setText(searchedResult.getOrderStatus());
                    } else {
                        JOptionPane.showMessageDialog(null, "OrderId Not Found.!");
                        lblGetSize.setText("");
                        lblGetQTY.setText("");
                        lblGetAmount.setText("");
                        lblGetCustID.setText("");
                        lblGetStatus.setText("");
                    }
                } catch (IOException ex) {
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
                    int selection = JOptionPane.showOptionDialog( null, "Please Select the Status", "Status", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, qIcon,buttons,buttons[0]);
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
                try {
                    boolean isUpdated = OrderController.updateOrderStatus(searchedResult);
                    if (isUpdated) {
                        JOptionPane.showMessageDialog(null, "Order Status Updated to : " + searchedResult.getOrderStatus());
                    }else{
                        JOptionPane.showMessageDialog(null, "Order Status Not Updated");
                    }
                } catch (IOException ex) {}
                
            }
        });
    }

    public void setOrderStatus(int status, String orderId) {
        if (searchedResult.getOrderId() != null && orderId.equals(searchedResult.getOrderId())) {
            if (status == 1) {
                searchedResult.setOrderStatus("Delivering");
            } else if (status == 2) {
                searchedResult.setOrderStatus("Delivered");
            }
        }
    }

    public int changeOrderStatus(String id) {
        if (searchedResult.getOrderId() != null && searchedResult.getOrderId().equalsIgnoreCase(id)) {
            if (searchedResult.getOrderStatus().equals("Processing")) {
                return 0;
            } else if (searchedResult.getOrderStatus().equals("Delivering")) {
                return 1;
            } else if (searchedResult.getOrderStatus().equals("Delivered")) {
                return 2;
            }
        }
        return -1;
    }
}
