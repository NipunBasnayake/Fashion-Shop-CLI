import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class StatusWindow extends JFrame {
    private JButton btnBack;
    private JButton btnSearch;
    private JButton btnChangeStatus;
    private JLabel lblOrderId;
    private JLabel lblCustomerId;
    private JLabel lblSize;
    private JLabel lblQty;
    private JLabel lblAmount;
    private JLabel lblStatus;
    private JLabel lblOrderIdShow;
    private JLabel lblSizeShow;
    private JLabel lblQtyShow;
    private JLabel lblAmountShow;
    private JLabel lblStatusShow;
    private JTextField txtCustomerId;

    StatusWindow(OrdersCollection ordersCollection) {
        setSize(500, 500);
        setTitle("Change Order Status");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Back button
        btnBack = new JButton("BACK");
        btnBack.setBackground(Color.RED);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(0, 0, 80, 30);
        add(btnBack);

        // Search button
        btnSearch = new JButton("SEARCH");
        btnSearch.setBackground(new Color(4, 203, 201));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBounds(390, 45, 90, 30);
        add(btnSearch);

        // Change Status Button
        btnChangeStatus = new JButton("CHANGE STATUS");
        btnChangeStatus.setFont(new Font("Arial", Font.BOLD, 12));
        btnChangeStatus.setBackground(new Color(135, 193, 255));
        btnChangeStatus.setForeground(Color.WHITE);
        btnChangeStatus.setBounds(320, 400, 150, 30);
        add(btnChangeStatus);

        // Back button Action
        btnBack.addActionListener(evt -> {
            dispose();
            new HomeWindow(ordersCollection).setVisible(true);
        });

        // Order ID label
        lblOrderId = new JLabel("Enter Order ID: ");
        lblOrderId.setFont(new Font("SanSerif", Font.BOLD, 15));
        lblOrderId.setBounds(40, 50, 200, 20);
        add(lblOrderId);

        // Enter Order ID text field
        txtCustomerId = new JTextField();
        txtCustomerId.setFont(new Font("SanSerif", Font.BOLD, 15));
        txtCustomerId.setBounds(180, 45, 180, 30);
        add(txtCustomerId);

        // Labels for showing order details
        lblCustomerId = new JLabel("Customer ID: ");
        lblCustomerId.setFont(new Font("SanSerif", Font.BOLD, 15));
        lblCustomerId.setBounds(40, 100, 200, 30);
        add(lblCustomerId);

        lblSize = new JLabel("Size: ");
        lblSize.setFont(new Font("SanSerif", Font.BOLD, 15));
        lblSize.setBounds(40, 150, 200, 30);
        add(lblSize);

        lblQty = new JLabel("QTY: ");
        lblQty.setFont(new Font("SanSerif", Font.BOLD, 15));
        lblQty.setBounds(40, 200, 200, 30);
        add(lblQty);

        lblAmount = new JLabel("Amount: ");
        lblAmount.setFont(new Font("SanSerif", Font.BOLD, 15));
        lblAmount.setBounds(40, 250, 200, 30);
        add(lblAmount);

        lblStatus = new JLabel("Status: ");
        lblStatus.setFont(new Font("SanSerif", Font.BOLD, 15));
        lblStatus.setBounds(40, 300, 200, 30);
        add(lblStatus);

        lblOrderIdShow = new JLabel();
        lblOrderIdShow.setFont(new Font("Arial", Font.BOLD, 15));
        lblOrderIdShow.setBounds(180, 100, 120, 30);
        add(lblOrderIdShow);

        lblSizeShow = new JLabel();
        lblSizeShow.setFont(new Font("Arial", Font.BOLD, 15));
        lblSizeShow.setBounds(180, 150, 200, 30);
        add(lblSizeShow);

        lblQtyShow = new JLabel();
        lblQtyShow.setFont(new Font("Arial", Font.BOLD, 15));
        lblQtyShow.setBounds(180, 200, 200, 30);
        add(lblQtyShow);

        lblAmountShow = new JLabel();
        lblAmountShow.setFont(new Font("Arial", Font.BOLD, 15));
        lblAmountShow.setBounds(180, 250, 200, 30);
        add(lblAmountShow);

        lblStatusShow = new JLabel();
        lblStatusShow.setFont(new Font("Arial", Font.BOLD, 15));
        lblStatusShow.setBounds(180, 300, 200, 30);
        add(lblStatusShow);

        btnSearch.addActionListener(evt -> {
            FashionShopCustomerDetails f1 = ordersCollection.searchOrderId(txtCustomerId.getText());
            if (f1 != null) {
                lblOrderIdShow.setText(f1.getPhoneNumber());
                lblSizeShow.setText(f1.getSize());
                lblQtyShow.setText(String.valueOf(f1.getQuantity()));
                lblAmountShow.setText(String.valueOf(f1.getAmount()));
                lblStatusShow.setText(String.valueOf(f1.printOrderStatus()));
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Order ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnChangeStatus.addActionListener(evt -> {
            int status = ordersCollection.changeOrderStatus(txtCustomerId.getText());
            if (status == 0) {
                Icon qIcon = UIManager.getIcon("JOptionPane.questionIcon");
                Object[] buttons = {"Delivering", "Delivered"};

                int selection = JOptionPane.showOptionDialog(
                    null,
                    "Please Select the Status",
                    "Status",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    qIcon,
                    buttons,
                    buttons[0]
                );
                switch (selection) {
                    case 0:
                        ordersCollection.setOrderStatus(1, txtCustomerId.getText());
                        break;
                    case 1:
                        ordersCollection.setOrderStatus(2, txtCustomerId.getText());
                        break;
                    default:
                        System.out.println("default");
                        break;
                }
            } else if (status == 1) {
                Icon qIcon = UIManager.getIcon("JOptionPane.questionIcon");
                Object[] button = {"Delivered"};

                int selection = JOptionPane.showOptionDialog(
                    null,
                    "Please select the Status",
                    "Status",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    qIcon,
                    button,
                    button[0]
                );
                switch (selection) {
                    case 0:
                        ordersCollection.setOrderStatus(2, txtCustomerId.getText());
                        break;
                    default:
                        System.out.println("Default");
                        break;
                }
            } else if (status == 2) {
                JOptionPane.showMessageDialog(null, "Can't change this order status... order already delivered...!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
