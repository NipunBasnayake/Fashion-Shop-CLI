import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class BestInCustomers extends JFrame{
    private JButton btnBack;
    private OrdersCollection ordersCollection;

    BestInCustomers(){
        setSize(500, 600);
        setTitle("Best In Customers");
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
                ViewReportsWindow viewReportsWindow = new ViewReportsWindow(ordersCollection);
                viewReportsWindow.setVisible(true);
            }
        });
    }
}
