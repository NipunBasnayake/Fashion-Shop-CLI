import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class PlaceOrderWindow extends JFrame {
    static int orderNumber = 1;

    private JLabel lblOrderId, lblPhoneNumber, lblTShirtSize, lblQTY, lblAmount;
    private JLabel lblOrderID, lblgetAmount;
    private JTextField txtPhoneNumber, txtTShirtSize, txtQTY;
    private JButton btnPlace, btnBack;
    private OrdersCollection ordersCollection;

    PlaceOrderWindow(OrdersCollection ordersCollection) {
        this.ordersCollection = ordersCollection;
        setSize(500, 600);
        setTitle("Place Order");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ----------------- Labels Panel -----------------
        JPanel labelPanel = new JPanel(new GridLayout(5, 1, 5, 10));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblOrderId = new JLabel("Order Id:");
        lblPhoneNumber = new JLabel("Phone Number:");
        lblTShirtSize = new JLabel("T-Shirt Size:");
        lblQTY = new JLabel("Quantity:");
        lblAmount = new JLabel("Amount:");

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        lblOrderId.setFont(labelFont);
        lblPhoneNumber.setFont(labelFont);
        lblTShirtSize.setFont(labelFont);
        lblQTY.setFont(labelFont);
        lblAmount.setFont(labelFont);

        labelPanel.add(lblOrderId);
        labelPanel.add(lblPhoneNumber);
        labelPanel.add(lblTShirtSize);
        labelPanel.add(lblQTY);
        labelPanel.add(lblAmount);
        add("West", labelPanel);

        // ----------------- Text Fields Panel -----------------
        JPanel txtPanel = new JPanel(new GridLayout(5, 1, 5, 10));
        txtPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblOrderID = new JLabel(generateOrderID());
        lblOrderID.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPanel.add(lblOrderID);

        txtPhoneNumber = new JTextField(20);
        txtTShirtSize = new JTextField(20);
        txtQTY = new JTextField(20);

        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);
        txtPhoneNumber.setFont(textFieldFont);
        txtTShirtSize.setFont(textFieldFont);
        txtQTY.setFont(textFieldFont);

        txtPanel.add(txtPhoneNumber);
        txtPanel.add(txtTShirtSize);
        txtPanel.add(txtQTY);

        lblgetAmount = new JLabel("");
        lblgetAmount.setFont(textFieldFont);
        txtPanel.add(lblgetAmount);
        add("Center", txtPanel);

        // ----------------- Back Button -----------------
        JPanel pnlBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setPreferredSize(new Dimension(100, 35));
        btnBack.setBackground(new Color(255, 102, 102));
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });
        pnlBack.add(btnBack);
        add("North", pnlBack);

        // ----------------- Place Button -----------------
        JPanel pnlPlace = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPlace = new JButton("Place");
        btnPlace.setFont(new Font("Arial", Font.BOLD, 16));
        btnPlace.setPreferredSize(new Dimension(100, 35));
        btnPlace.setBackground(new Color(4, 203, 201));
        btnPlace.setForeground(Color.WHITE);

        btnPlace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (validatePhoneNumber() && validateSize() && validateQty()) {
                    // System.out.println("OrdersCollection instance: " + ordersCollection);
                    // System.out.println("Order ID label: " + lblOrderID);

                    double amount = calculateAmount();
                    lblgetAmount.setText(String.valueOf(amount));

                    String orderID = lblOrderID.getText();
                    String size = txtTShirtSize.getText();
                    int qty = Integer.parseInt(txtQTY.getText());
                    String cusID = txtPhoneNumber.getText();
                    String orderStatus = "Processing";

                    Order newOrder = new Order(orderID, size, qty, amount, cusID, orderStatus);
                    ordersCollection.addOrder(newOrder);
                    orderNumber++;
                    JOptionPane.showMessageDialog(PlaceOrderWindow.this, "Order placed..!", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                }
            }
        });

        pnlPlace.add(btnPlace);
        add("South", pnlPlace);
    }

    public String generateOrderID() {
        int tempOrderNumber = orderNumber;

        int[] tempNumOrder = new int[5];
        String idNum = "";
        String tag = "ODR#";
        String newOrderID = "";

        for (int i = 4; tempOrderNumber > 0; i--) {
            tempNumOrder[i] = tempOrderNumber % 10;
            tempOrderNumber /= 10;
        }
        for (int i = 0; i < tempNumOrder.length; i++) {
            idNum += tempNumOrder[i];
        }
        newOrderID = tag + idNum;
        return newOrderID;
    }

    public boolean validateSize() {
        String tShirtSize = txtTShirtSize.getText();
        if (tShirtSize == null || tShirtSize.isEmpty()) {
            JOptionPane.showMessageDialog(this, "T-Shirt Size cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!tShirtSize.equalsIgnoreCase("XS") && !tShirtSize.equalsIgnoreCase("S") &&
                !tShirtSize.equalsIgnoreCase("M") && !tShirtSize.equalsIgnoreCase("L") &&
                !tShirtSize.equalsIgnoreCase("XL") && !tShirtSize.equalsIgnoreCase("XXL")) {
            JOptionPane.showMessageDialog(this, "Invalid size.. Try again", "Error", JOptionPane.ERROR_MESSAGE);
            txtTShirtSize.setText("");
            return false;
        }
        return true;
    }

    public boolean validateQty() {
        try {
            int qty = Integer.parseInt(txtQTY.getText());
            if (qty <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than 0", "Error",
                        JOptionPane.ERROR_MESSAGE);
                txtQTY.setText("");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for Quantity", "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtQTY.setText("");
            return false;
        }
        return true;
    }

    public double calculateAmount() {
        String tShirtSize = txtTShirtSize.getText();
        int qty = Integer.parseInt(txtQTY.getText());
        double amount = 0.0;
        switch (tShirtSize.toUpperCase()) {
            case "XS":
                amount = qty * 600.00;
                break;
            case "S":
                amount = qty * 800.00;
                break;
            case "M":
                amount = qty * 900.00;
                break;
            case "L":
                amount = qty * 1000.00;
                break;
            case "XL":
                amount = qty * 1100.00;
                break;
            case "XXL":
                amount = qty * 1200.00;
                break;
        }
        return amount;
    }

    public boolean validatePhoneNumber() {
        String cusPhoneNumber = txtPhoneNumber.getText();
        if (cusPhoneNumber == null || cusPhoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone number cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (cusPhoneNumber.length() != 10 || cusPhoneNumber.charAt(0) != '0') {
            JOptionPane.showMessageDialog(this, "Invalid Number.. Try again", "Error", JOptionPane.ERROR_MESSAGE);
            txtPhoneNumber.setText("");
            return false;
        }
        return true;
    }

    private void clearFields() {
        txtPhoneNumber.setText("");
        txtTShirtSize.setText("");
        txtQTY.setText("");
        lblgetAmount.setText("");
        lblOrderID.setText(generateOrderID());
    }
}
