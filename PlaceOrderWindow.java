import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

class PlaceOrderWindow extends JFrame {
    static int orderNumber = 1;
    
    private JLabel lblTitle;
    private JLabel lblOrderId;
    private JLabel lblPhoneNumber;
    private JLabel lblTShirtSize;
    private JLabel lblQTY;
    private JLabel lblAmount;
    
    private JLabel lblOrderID;

    private JTextField txtPhoneNumber;
    private JTextField txtTShirtSize;
    private JTextField txtQTY;
    private JTextField txtAmount;
    
    private JButton btnSubmit;
    private JButton btnClear;
    private JButton btnBackToHome;

    PlaceOrderWindow() {
        setSize(700, 450);
        setTitle("Place Order | Fashion Shop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        lblTitle = new JLabel("PLACE ORDER");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); 
        add("North", lblTitle);

        JPanel labelPanel = new JPanel(new GridLayout(5, 1, 5, 10));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblOrderId = new JLabel("Order Id:");
        lblPhoneNumber = new JLabel("Phone Number:");
        lblTShirtSize = new JLabel("T-Shirt Size:");
        lblQTY = new JLabel("Quantity:");
        lblAmount = new JLabel("Amount:");

        JLabel[] labels = {lblOrderId, lblPhoneNumber, lblTShirtSize, lblQTY, lblAmount};
        for (JLabel label : labels) {
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setHorizontalAlignment(JLabel.RIGHT);
            labelPanel.add(label);
        }
        add("West", labelPanel);

        JPanel txtPanel = new JPanel(new GridLayout(5, 1, 5, 10));
        txtPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblOrderID = new JLabel(generateOrderID());
        lblOrderID.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPanel.add(lblOrderID);

        txtPhoneNumber = new JTextField(20);
        txtTShirtSize = new JTextField(20);
        txtQTY = new JTextField(20);
        txtAmount = new JTextField(20);

        JTextField[] textFields = { txtPhoneNumber, txtTShirtSize, txtQTY, txtAmount};
        for (JTextField textField : textFields) {
            textField.setFont(new Font("Arial", Font.PLAIN, 16));
            txtPanel.add(textField);
        }

        add("Center", txtPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        btnSubmit = new JButton("Submit Order");
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 16));
        btnSubmit.setPreferredSize(new Dimension(170, 40));

        btnClear = new JButton("Clear All");
        btnClear.setFont(new Font("Arial", Font.BOLD, 16));
        btnClear.setPreferredSize(new Dimension(170, 40));
        
        btnBackToHome = new JButton("Back to Home");
        btnBackToHome.setFont(new Font("Arial", Font.BOLD, 16));
        btnBackToHome.setPreferredSize(new Dimension(170, 40));

        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnBackToHome);

        add("South", buttonPanel);

        btnSubmit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                validatePhoneNumber();
                validateSize();
                validateQty();
            }
        });

        btnClear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                txtPhoneNumber.setText("");
                txtTShirtSize.setText("");
                txtQTY.setText("");
                txtAmount.setText("");
            }
        });

        btnBackToHome.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                dispose();
            }
        });
    }

    public static String generateOrderID() {
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

    public void validatePhoneNumber() {
        String cusPhoneNumber = txtPhoneNumber.getText();
        if (cusPhoneNumber == null || cusPhoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone number cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        }else if (cusPhoneNumber.length() != 10 || cusPhoneNumber.charAt(0) != '0') {
            JOptionPane.showMessageDialog(this, "Invalid Number.. Try again", "Error", JOptionPane.ERROR_MESSAGE);
            txtPhoneNumber.setText("");
        }
    }

    public void validateSize() {
        String tShirtSize = txtTShirtSize.getText();
    
        if (tShirtSize == null || tShirtSize.isEmpty()) {
            JOptionPane.showMessageDialog(this, "T-Shirt Size cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!tShirtSize.equalsIgnoreCase("XS") && 
            !tShirtSize.equalsIgnoreCase("S") && 
            !tShirtSize.equalsIgnoreCase("M") && 
            !tShirtSize.equalsIgnoreCase("L") && 
            !tShirtSize.equalsIgnoreCase("XL") && 
            !tShirtSize.equalsIgnoreCase("XXL")) {
            JOptionPane.showMessageDialog(this, "Invalid size.. Try again", "Error", JOptionPane.ERROR_MESSAGE);
            txtTShirtSize.setText("");
        }
    }

    public void validateQty() {
		int qty = Integer.parseInt(txtQTY.getText());

		if (qty <= 0) {
            JOptionPane.showMessageDialog(this, "Phone number cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            txtQTY.setText("");
        }
	}


    
}
