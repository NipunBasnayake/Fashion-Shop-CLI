import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchOrderWindow extends JFrame {

    private JLabel lblCustID, lblSize, lblQTY, lblAmount, lblStatus;
    private JLabel lblGetCustID, lblGetSize, lblGetQTY, lblGetAmount, lblGetStatus;
    private JButton btnBack, btnSearch;
    private JLabel lblEnterID;
    private JTextField txtOrderID;

    private OrdersCollection ordersCollection; 

    SearchOrderWindow(OrdersCollection ordersCollection) {
        this.ordersCollection = ordersCollection;
        setSize(500, 600);
        setTitle("Fashion Shop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ----------------- Back Button Panel -----------------
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
        add(pnlBack, BorderLayout.NORTH);

        // ----------------- Search Panel -----------------
        JPanel searchPanel = new JPanel(new FlowLayout());
        lblEnterID = new JLabel("Enter Order ID:");
        txtOrderID = new JTextField(15);
        btnSearch = new JButton("Search");

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String orderId = txtOrderID.getText().trim();
                Order[] foundOrders = ordersCollection.searchOrderID(orderId);

                if (foundOrders.length > 0) {
                    lblGetCustID.setText(foundOrders[0].getCustomerID());
                    lblGetSize.setText(foundOrders[0].getSize());
                    lblGetQTY.setText(String.valueOf(foundOrders[0].getQuantity()));
                    lblGetAmount.setText(String.valueOf(foundOrders[0].getAmount()));
                    lblGetStatus.setText(foundOrders[0].getOrderStatus());
                } else {
                    JOptionPane.showMessageDialog(
                    SearchOrderWindow.this,
                    "Invalid Order ID",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
                );
                }
            }
        });

        searchPanel.add(lblEnterID);
        searchPanel.add(txtOrderID);
        searchPanel.add(btnSearch);

        // ----------------- Detail Panel -----------------
        JPanel lblPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        lblCustID = new JLabel("Customer ID:");
        lblSize = new JLabel("Size:");
        lblQTY = new JLabel("Quantity:");
        lblAmount = new JLabel("Amount:");
        lblStatus = new JLabel("Status:");

        lblGetCustID = new JLabel("");
        lblGetSize = new JLabel("");
        lblGetQTY = new JLabel("");
        lblGetAmount = new JLabel("");
        lblGetStatus = new JLabel("");

        lblCustID.setFont(new Font("Arial", Font.BOLD, 16));
        lblSize.setFont(new Font("Arial", Font.BOLD, 16));
        lblQTY.setFont(new Font("Arial", Font.BOLD, 16));
        lblAmount.setFont(new Font("Arial", Font.BOLD, 16));
        lblStatus.setFont(new Font("Arial", Font.BOLD, 16));

        lblGetCustID.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetSize.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetQTY.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetStatus.setFont(new Font("Arial", Font.PLAIN, 16));

        lblPanel.add(lblCustID);
        lblPanel.add(lblGetCustID);
        lblPanel.add(lblSize);
        lblPanel.add(lblGetSize);
        lblPanel.add(lblQTY);
        lblPanel.add(lblGetQTY);
        lblPanel.add(lblAmount);
        lblPanel.add(lblGetAmount);
        lblPanel.add(lblStatus);
        lblPanel.add(lblGetStatus);

        // ----------------- Center Panel -----------------
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(lblPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }
}
