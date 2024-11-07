import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViewReportsWindow extends JFrame {
    private JButton btnBack;
    private JButton ViewCustomers;
    private JButton BestInCustomers;
    private JButton AllCustomers;
    private JButton CategorizedByQty;
    private JButton CategorizedByAmount;
    private JButton OrderByAmount;
    private JButton AllOrders;

    ViewReportsWindow(OrdersCollection ordersCollection) {
        setSize(500, 550);
        setTitle("View Reports");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBackground(new Color(255, 102, 102));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(10, 10, 100, 35);
        add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new HomeWindow(ordersCollection).setVisible(true);
            }
        });

        int buttonWidth = 250;
        int buttonHeight = 35;
        int centerX = (getWidth() - buttonWidth) / 2;

        ViewCustomers = new JButton("View Customers");
        ViewCustomers.setFont(new Font("Arial", Font.BOLD, 16));
        ViewCustomers.setBackground(new Color(0, 204, 51));
        ViewCustomers.setForeground(Color.WHITE);
        ViewCustomers.setBounds(centerX, 100, buttonWidth, buttonHeight);
        add(ViewCustomers);
        ViewCustomers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new ViewCustomers(ordersCollection).setVisible(true);
                dispose();
            }
        });

        BestInCustomers = new JButton("Best In Customers");
        BestInCustomers.setFont(new Font("Arial", Font.BOLD, 16));
        BestInCustomers.setBackground(new Color(0, 204, 51));
        BestInCustomers.setForeground(Color.WHITE);
        BestInCustomers.setBounds(centerX, 150, buttonWidth, buttonHeight);
        add(BestInCustomers);
        BestInCustomers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new BestInCustomers(ordersCollection).setVisible(true);
                dispose();
            }
        });

        AllCustomers = new JButton("All Customers");
        AllCustomers.setFont(new Font("Arial", Font.BOLD, 16));
        AllCustomers.setBackground(new Color(0, 204, 51));
        AllCustomers.setForeground(Color.WHITE);
        AllCustomers.setBounds(centerX, 200, buttonWidth, buttonHeight);
        add(AllCustomers);
        AllCustomers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new AllCustomers(ordersCollection).setVisible(true);
            dispose();
            }
        });

        CategorizedByQty = new JButton("Categorized By Qty");
        CategorizedByQty.setFont(new Font("Arial", Font.BOLD, 16));
        CategorizedByQty.setBackground(new Color(0, 51, 153));
        CategorizedByQty.setForeground(Color.WHITE);
        CategorizedByQty.setBounds(centerX, 250, buttonWidth, buttonHeight);
        add(CategorizedByQty);
        CategorizedByQty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new CategorazedByQty(ordersCollection).setVisible(true);
            dispose();
            }
        });

        CategorizedByAmount = new JButton("Categorized By Amount");
        CategorizedByAmount.setFont(new Font("Arial", Font.BOLD, 16));
        CategorizedByAmount.setBackground(new Color(0, 51, 153));
        CategorizedByAmount.setForeground(Color.WHITE);
        CategorizedByAmount.setBounds(centerX, 300, buttonWidth, buttonHeight);
        add(CategorizedByAmount);
        CategorizedByAmount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new CategorazedByAmount(ordersCollection).setVisible(true);
                dispose();
            }
        });

        OrderByAmount = new JButton("Order By Amount");
        OrderByAmount.setFont(new Font("Arial", Font.BOLD, 16));
        OrderByAmount.setBackground(new Color(102, 102, 102));
        OrderByAmount.setForeground(Color.WHITE);
        OrderByAmount.setBounds(centerX, 350, buttonWidth, buttonHeight);
        add(OrderByAmount);
        OrderByAmount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new OrderByAmount(ordersCollection).setVisible(true);
            dispose();
            }
        });

        AllOrders = new JButton("All Orders");
        AllOrders.setFont(new Font("Arial", Font.BOLD, 16));
        AllOrders.setBackground(new Color(102, 102, 102));
        AllOrders.setForeground(Color.WHITE);
        AllOrders.setBounds(centerX, 400, buttonWidth, buttonHeight);
        add(AllOrders);
        AllOrders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new AllOrders(ordersCollection).setVisible(true);
                dispose();
            }
        });
    }
}
