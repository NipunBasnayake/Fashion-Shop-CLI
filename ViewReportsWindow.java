import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViewReportsWindow extends JFrame {
    private JButton btnBack;
    private JButton ViewCustomers;
    private JButton BestInCustomers;
    private JButton AllCustomers;
    private JButton CategorazedByQty;
    private JButton CategorazedByAmount;
    private JButton OrderByAmount;
    private JButton AllOrders;

    private OrdersCollection ordersCollection;

    ViewReportsWindow(OrdersCollection ordersCollection) {
        this.ordersCollection = ordersCollection;

        setSize(500, 600);
        setTitle("View Reports");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBackground(new Color(255, 102, 102));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(0, 0, 100, 35);
        add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                HomeWindow homeWindow = new HomeWindow();
                homeWindow.setVisible(true);
            }
        });

        // --------------------------------
        ViewCustomers = new JButton("View Customers");
        ViewCustomers.setFont(new Font("Arial", Font.BOLD, 16));
        ViewCustomers.setBackground(new Color(0, 204, 51));
        ViewCustomers.setForeground(Color.WHITE);
        ViewCustomers.setBounds(0, 150, 150, 35);
        add(ViewCustomers);
        ViewCustomers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ViewCustomers viewCustomers = new ViewCustomers();
                viewCustomers.setVisible(true);
                dispose();
            }
        });

        BestInCustomers = new JButton("Best In Customers");
        BestInCustomers.setFont(new Font("Arial", Font.BOLD, 16));
        BestInCustomers.setBackground(new Color(0, 204, 51));
        BestInCustomers.setForeground(Color.WHITE);
        BestInCustomers.setBounds(0, 200, 150, 35);
        add(BestInCustomers);
        BestInCustomers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BestInCustomers bestInCustomers = new BestInCustomers();
                bestInCustomers.setVisible(true);
                dispose();
            }
        });

        AllCustomers = new JButton("All Customers");
        AllCustomers.setFont(new Font("Arial", Font.BOLD, 16));
        AllCustomers.setBackground(new Color(0, 204, 51));
        AllCustomers.setForeground(Color.WHITE);
        AllCustomers.setBounds(0, 250, 150, 35);
        add(AllCustomers);
        AllCustomers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AllCustomers allCustomers = new AllCustomers();
                allCustomers.setVisible(true);
                dispose();
            }
        });

        // --------------------------------
        CategorazedByQty = new JButton("Categorized By Qty");
        CategorazedByQty.setFont(new Font("Arial", Font.BOLD, 16));
        CategorazedByQty.setBackground(new Color(0, 51, 153));
        CategorazedByQty.setForeground(Color.WHITE);
        CategorazedByQty.setBounds(170, 175, 150, 35);
        add(CategorazedByQty);
        CategorazedByQty.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                CategorazedByQty categorazedByQty = new CategorazedByQty();
                categorazedByQty.setVisible(true);
                dispose();
            }
        });

        CategorazedByAmount = new JButton("Categorized By Amount");
        CategorazedByAmount.setFont(new Font("Arial", Font.BOLD, 16));
        CategorazedByAmount.setBackground(new Color(0, 51, 153));
        CategorazedByAmount.setForeground(Color.WHITE);
        CategorazedByAmount.setBounds(170, 225, 150, 35);
        add(CategorazedByAmount);
        CategorazedByAmount.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                CategorazedByAmount categorazedByAmount = new CategorazedByAmount();
                categorazedByAmount.setVisible(true);
                dispose();
            }
        });

        // --------------------------------
        OrderByAmount = new JButton("Order By Amount");
        OrderByAmount.setFont(new Font("Arial", Font.BOLD, 16));
        OrderByAmount.setBackground(new Color(102, 102, 102));
        OrderByAmount.setForeground(Color.WHITE);
        OrderByAmount.setBounds(340, 175, 150, 35);
        add(OrderByAmount);
        OrderByAmount.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                OrderByAmount orderByAmount = new OrderByAmount();
                orderByAmount.setVisible(true);
                dispose();
            }
        });

        AllOrders = new JButton("All Orders");
        AllOrders.setFont(new Font("Arial", Font.BOLD, 16));
        AllOrders.setBackground(new Color(102, 102, 102));
        AllOrders.setForeground(Color.WHITE);
        AllOrders.setBounds(340, 225, 150, 35);
        add(AllOrders);
        AllOrders.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                AllOrders allOrders = new AllOrders();
                allOrders.setVisible(true);
                dispose();
            }
        });
    }
}
