import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

class CategorazedByQty extends JFrame {
    private JButton btnBack;

    CategorazedByQty(List ordersCollection) {
        setSize(500, 550);
        setTitle("Categorazed By Qty");
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

                ViewReportsWindow viewReportsWindow = new ViewReportsWindow(ordersCollection);
                viewReportsWindow.setVisible(true);
                dispose();
            }
        });

        String[] columns = { "Size", "QTY", "Amount" };
        DefaultTableModel table = new DefaultTableModel(columns, 0);

        Order[] sortedArray = ordersCollection.sortByQty();
        for (int i = 0; i < sortedArray.length; i++) {
            Object[] rowData = { sortedArray[i].getSize(), sortedArray[i].getQuantity(), sortedArray[i].getAmount() };
            table.addRow(rowData);
        }

        JTable custTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(custTable);
        scrollPane.setBounds(20, 80, 440, 400);
        add(scrollPane);

    }
}
