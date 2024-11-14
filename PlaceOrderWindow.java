import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class PlaceOrderWindow extends JFrame {
    static int orderNumber = 1;

    private JLabel lblOrderId, lblPhoneNumber, lblTShirtSize, lblQTY, lblAmount;
    private JLabel lblOrderID, lblgetAmount;
    private JTextField txtPhoneNumber, txtTShirtSize, txtQTY;
    private JButton btnPlace, btnBack;

    PlaceOrderWindow(List ordersCollection) {
        setSize(500, 550);
        setTitle("Place Order");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        lblOrderID = new JLabel();
        lblOrderID.setFont(new Font("Arial", Font.PLAIN, 16));
        lblOrderID.setBounds(180, 100, 250, 35);
        add(lblOrderID);
        lblOrderID.setText(generateOrderId());

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBackground(new Color(255, 102, 102));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(50, 20, 100, 35);
        add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new HomeWindow(ordersCollection).setVisible(true);
                dispose();
            }
        });

        lblOrderId = new JLabel("Order Id:");
        lblOrderId.setFont(new Font("Arial", Font.BOLD, 16));
        lblOrderId.setBounds(50, 100, 150, 35);
        add(lblOrderId);

        lblPhoneNumber = new JLabel("Phone Number:");
        lblPhoneNumber.setFont(new Font("Arial", Font.BOLD, 16));
        lblPhoneNumber.setBounds(50, 170, 150, 35);
        add(lblPhoneNumber);

        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setBounds(180, 170, 250, 35);
        txtPhoneNumber.setFont(new Font("Arial", Font.BOLD, 16));
        add(txtPhoneNumber);

        lblTShirtSize = new JLabel("T-Shirt Size:");
        lblTShirtSize.setFont(new Font("Arial", Font.BOLD, 16));
        lblTShirtSize.setBounds(50, 240, 150, 35);
        add(lblTShirtSize);

        txtTShirtSize = new JTextField();
        txtTShirtSize.setBounds(180, 240, 250, 35);
        txtTShirtSize.setFont(new Font("Arial", Font.BOLD, 16));
        add(txtTShirtSize);

        lblQTY = new JLabel("Quantity:");
        lblQTY.setFont(new Font("Arial", Font.BOLD, 16));
        lblQTY.setBounds(50, 310, 150, 35);
        add(lblQTY);

        txtQTY = new JTextField();
        txtQTY.setBounds(180, 310, 250, 35);
        txtQTY.setFont(new Font("Arial", Font.BOLD, 16));
        add(txtQTY);
        txtQTY.addActionListener(evt -> {
            lblgetAmount.setText(String.format("%.2f", calculateAmount(txtTShirtSize.getText(), txtQTY.getText().isEmpty() ? 0 : Integer.parseInt(txtQTY.getText()))));
        });

        lblAmount = new JLabel("Amount:");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 16));
        lblAmount.setBounds(50, 380, 150, 35);
        add(lblAmount);

        lblgetAmount = new JLabel();
        lblgetAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        lblgetAmount.setBounds(180, 380, 250, 35);
        add(lblgetAmount);

        lblgetAmount.setText(String.format("%.2f", calculateAmount(txtTShirtSize.getText(), txtQTY.getText().isEmpty() ? 0 : Integer.parseInt(txtQTY.getText()))));

        btnPlace = new JButton("Place");
        btnPlace.setFont(new Font("Arial", Font.BOLD, 16));
        btnPlace.setBackground(new Color(4, 203, 201));
        btnPlace.setForeground(Color.WHITE);
        btnPlace.setBounds(300, 430, 125, 50);
        add(btnPlace);
        btnPlace.addActionListener(evt -> {
            if (validatePhoneNumber() && validateSize() && validateQty()) {
                String orderID = lblOrderID.getText();
                String size = txtTShirtSize.getText();
                int qty = Integer.parseInt(txtQTY.getText());
                double amount = Double.parseDouble(lblgetAmount.getText());
                String cusID = txtPhoneNumber.getText();
                String orderStatus = "Processing";
                Order newOrder = new Order(orderID, size, qty, amount, cusID, orderStatus);

                try {
                    FileWriter fw = new FileWriter("OrdersDoc.txt", true);
                    fw.write(newOrder.toString() + "\n");
                    fw.close();
                    JOptionPane.showMessageDialog(this, "Order placed!", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Order not placed!", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                lblOrderID.setText(generateOrderId());
                txtPhoneNumber.setText("");
                txtTShirtSize.setText("");
                txtQTY.setText("");
            }
        });
    }

    private String generateOrderId() {
        String lastLine = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("OrdersDoc.txt"));
            String line = br.readLine();
            while (line != null) {
                lastLine = line;
                line = br.readLine();
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (lastLine == null || lastLine.length() < 9) {
            return "ODR#00001";
        } else {
            int lastIdNumber = Integer.parseInt(lastLine.substring(4, 9));
            return String.format("ODR#%05d", lastIdNumber + 1);
        }
    }

    public boolean validateSize() {
        String tShirtSize = txtTShirtSize.getText();
        if (tShirtSize == null || tShirtSize.isEmpty()) {
            JOptionPane.showMessageDialog(this, "T-Shirt Size cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!tShirtSize.matches("(?i)XS|S|M|L|XL|XXL")) {
            JOptionPane.showMessageDialog(this, "Invalid size. Try again", "Error", JOptionPane.ERROR_MESSAGE);
            txtTShirtSize.setText("");
            return false;
        }
        return true;
    }

    public boolean validateQty() {
        String qtyText = txtQTY.getText();
        if (qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Quantity cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    

    public boolean validatePhoneNumber() {
        String phone = txtPhoneNumber.getText();
        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Invalid Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public double calculateAmount(String tShirtSize, int qty) {
        double amount = 0;
        switch (tShirtSize) {
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
}