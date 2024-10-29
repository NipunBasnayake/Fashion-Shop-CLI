import javax.swing.*;
import java.awt.*;

class PlaceOrderWindow extends JFrame {
    private JLabel lblTitle;

    private JLabel lblOrderId;
    private JLabel lblPhoneNumber;
    private JLabel lblTShirtSize;
    private JLabel lblQTY;
    private JLabel lblAmount;
    
    private JTextField txtOrderId;
    private JTextField txtPhoneNumber;
    private JTextField txtTShirtSize;
    private JTextField txtQTY;
    private JTextField txtAmount;
    
    private JButton btnSubmit;
    private JButton btnClear;
    private JButton btnBackToHome;

    PlaceOrderWindow() {
		setSize(700, 450);
        setTitle("Fashion Shop | Place Order");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        lblTitle = new JLabel("PLACE ORDER");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); 
        add("North",lblTitle);

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

        txtOrderId = new JTextField(20);
        txtPhoneNumber = new JTextField(20);
        txtTShirtSize = new JTextField(20);
        txtQTY = new JTextField(20);
        txtAmount = new JTextField(20);

        JTextField[] textFields = {txtOrderId, txtPhoneNumber, txtTShirtSize, txtQTY, txtAmount};
        for (JTextField textField : textFields) {
            textField.setFont(new Font("Arial", Font.PLAIN, 16));
            txtPanel.add(textField);
        }

        add(txtPanel, BorderLayout.CENTER);

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
    }
}
