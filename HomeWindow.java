import javax.swing.*;
import java.awt.*;

class HomeWindow extends JFrame {
    private JLabel lblTitle;
    private JButton btnPlaceOrder;
    private JButton btnSearchCustomer;
    private JButton btnSearchOrder;
    private JButton btnViewReports;
    private JButton btnSetOrderStatus;
    private JButton btnDeleteOrder;

    HomeWindow() {
        setSize(700, 450);
        setTitle("Fashion Shop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        lblTitle = new JLabel("FASHION SHOP");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add("North",lblTitle);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        btnPlaceOrder = createStyledButton("Place Order");
        btnSearchCustomer = createStyledButton("Search Customer");
        btnSearchOrder = createStyledButton("Search Order");
        btnViewReports = createStyledButton("View Reports");
        btnSetOrderStatus = createStyledButton("Set Order Status");
        btnDeleteOrder = createStyledButton("Delete Order");

        buttonPanel.add(btnPlaceOrder);
        buttonPanel.add(btnSearchCustomer);
        buttonPanel.add(btnSearchOrder);
        buttonPanel.add(btnViewReports);
        buttonPanel.add(btnSetOrderStatus);
        buttonPanel.add(btnDeleteOrder);

        add("Center", buttonPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(150, 50));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
}
