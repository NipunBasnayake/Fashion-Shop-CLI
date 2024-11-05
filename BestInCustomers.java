import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
class BestInCustomers extends JFrame{
    private JButton btnBack;

    BestInCustomers(OrdersCollection ordersCollection){
        setSize(500, 550);
        setTitle("Best In Customers");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBackground(new Color(255, 102, 102));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(20, 20, 100, 35);
        add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new ViewReportsWindow(ordersCollection).setVisible(true);
            }
        });

        String[] columns = {"Customer ID","Quantity","Amount"};
        DefaultTableModel table = new DefaultTableModel(columns,0);

        Order[] bestInCustomers=ordersCollection.bestInCustomers();
        for(int i=0; i<bestInCustomers.length; i++){
            if(bestInCustomers[i]!=null){
                if(bestInCustomers[i].getQuantity()!=0){
                    Object[] rowData = {bestInCustomers[i].getCustomerID(),bestInCustomers[i].getQuantity(),bestInCustomers[i].getAmount()};
                    table.addRow(rowData);
                }
            }
        }

        JTable cusTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(cusTable);
        scrollPane.setBounds(20, 80, 440, 400);       
        add(scrollPane);
    }
}
