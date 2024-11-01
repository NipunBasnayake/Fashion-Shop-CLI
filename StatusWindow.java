import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class StatusWindow extends JFrame{
    private JButton btnBack;
    private OrdersCollection ordersCollection;

    StatusWindow(OrdersCollection ordersCollection){
        setSize(500,600);
        setTitle("Change Status");
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
    }
}
