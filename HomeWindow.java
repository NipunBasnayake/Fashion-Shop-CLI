import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HomeWindow extends JFrame {
    private JLabel lblTitle;
    private JLabel copyrightLabel;
    private JLabel imageLabel;

    private JButton btnSearch;
    private JButton btnViewReports;
    private JButton btnSetOrderStatus;
    private JButton btnDeleteOrder;
    private JButton btnPlaceOrder;

    HomeWindow() {
        setSize(500, 600);
        setTitle("Fashion Shop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); 

        // ----------------- Title --------------------
        JPanel pnlNorth = new JPanel();
        pnlNorth.setBackground(new Color(51, 102, 255));
        pnlNorth.setLayout(new BorderLayout());

        lblTitle = new JLabel("FASHION SHOP", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        pnlNorth.add(lblTitle, BorderLayout.CENTER);
        add(pnlNorth, BorderLayout.NORTH);

        // ----------------- Buttons --------------------
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        btnSearch = new JButton("Search");
        btnViewReports = new JButton("Reports");
        btnSetOrderStatus = new JButton("Status");
        btnDeleteOrder = new JButton("Delete");
        btnPlaceOrder = new JButton("Place Order");

        btnSearch.setFont(new Font("Arial", Font.BOLD, 16));
        btnViewReports.setFont(new Font("Arial", Font.BOLD, 16));
        btnSetOrderStatus.setFont(new Font("Arial", Font.BOLD, 16));
        btnDeleteOrder.setFont(new Font("Arial", Font.BOLD, 16));
        btnPlaceOrder.setFont(new Font("Arial", Font.BOLD, 16));
        
        btnSearch.setPreferredSize(new Dimension(150, 50));
        btnViewReports.setPreferredSize(new Dimension(150, 50));
        btnSetOrderStatus.setPreferredSize(new Dimension(150, 50));
        btnDeleteOrder.setPreferredSize(new Dimension(150, 50));
        btnPlaceOrder.setPreferredSize(new Dimension(150, 70));

        btnPlaceOrder.setBackground(new Color(4, 203, 201));
        btnPlaceOrder.setForeground(Color.WHITE);


        buttonPanel.add(btnSearch);
        buttonPanel.add(btnViewReports);
        buttonPanel.add(btnSetOrderStatus);
        buttonPanel.add(btnDeleteOrder);
        buttonPanel.add(btnPlaceOrder);
        add("West", buttonPanel);

        // ----------------- Image --------------------
        imageLabel = new JLabel(new ImageIcon("fashion_image.jpg"));
        imageLabel.setBounds(200, 80, 150, 300);
        add("Center", imageLabel);

        // ----------------- Footer Label --------------------
        copyrightLabel = new JLabel("Copyrights © Nipun 2024", SwingConstants.CENTER);
        copyrightLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add("South", copyrightLabel);

        // ----------------- Button Actions --------------------
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Object[] options = {"Search Customer", "Search Order", "Cancel"};
                int response = JOptionPane.showOptionDialog(
                    null,
                    "Please select the option",
                    "Search Options",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
                );

                switch (response) {
                    case 0:
                        SearchCustomerWindow searchCustomerWindow = new SearchCustomerWindow();
                        searchCustomerWindow.setVisible(true);
                        break;
                    case 1:
                        SearchOrderWindow searchOrderWindow = new SearchOrderWindow();
                        searchOrderWindow.setVisible(true);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Unexpected response.");
                        break;
                }
            }
        });
        




    }
}

