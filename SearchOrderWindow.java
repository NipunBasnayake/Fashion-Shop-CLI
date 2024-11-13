import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SearchOrderWindow extends JFrame {

    private JLabel lblCustID, lblSize, lblQTY, lblAmount, lblStatus;
    private JLabel lblGetCustID, lblGetSize, lblGetQTY, lblGetAmount, lblGetStatus;
    private JButton btnBack, btnSearch;
    private JLabel lblEnterID;
    private JTextField txtOrderID;

    SearchOrderWindow(List ordersCollection) {
        setSize(500, 550);
        setTitle("Fashion Shop");
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
            }
        });

        lblEnterID = new JLabel("Enter Order ID");
        lblEnterID.setFont(new Font("Arial", Font.BOLD, 16));
        lblEnterID.setBounds(20, 85, 150, 30);
        add(lblEnterID);

        txtOrderID = new JTextField(15);
        txtOrderID.setFont(new Font("Arial", Font.BOLD, 16));
        txtOrderID.setBounds(170, 85, 180, 30);
        add(txtOrderID);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(360, 85, 100, 30);
        add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String newLine = null;
                try {
                    BufferedReader br = new BufferedReader(new FileReader("OrdersDoc.txt"));
                    String line = br.readLine();

                    while (line != null) {
                        String orderId = line.substring(0,9);
                        if (orderId.equalsIgnoreCase(txtOrderID.getText())) {
                            newLine = line;
                            break;
                        }
                        line = br.readLine();
                    }

                } catch (IOException ex) {
                    
                }
                if (newLine != null) {
                    String[] rowData = newLine.split(",");
                    lblGetSize.setText(rowData[1]);
                    lblGetQTY.setText(rowData[2]);
                    lblGetAmount.setText(rowData[3]);
                    lblGetCustID.setText(rowData[4]);
                    lblGetStatus.setText(rowData[5]);
                }else{
                    JOptionPane.showMessageDialog(null, "Customer not Found");
                }
            }
        });

        lblCustID = new JLabel("Customer ID:");
        lblCustID.setFont(new Font("Arial", Font.BOLD, 16));
        lblCustID.setBounds(20, 170, 150, 25);
        add(lblCustID);

        lblSize = new JLabel("Size:");
        lblSize.setFont(new Font("Arial", Font.BOLD, 16));
        lblSize.setBounds(20, 220, 150, 25);
        add(lblSize);

        lblQTY = new JLabel("Quantity:");
        lblQTY.setFont(new Font("Arial", Font.BOLD, 16));
        lblQTY.setBounds(20, 270, 150, 25);
        add(lblQTY);

        lblAmount = new JLabel("Amount:");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 16));
        lblAmount.setBounds(20, 320, 150, 25);
        add(lblAmount);

        lblStatus = new JLabel("Status:");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
        lblStatus.setBounds(20, 370, 150, 25);
        add(lblStatus);

        lblGetCustID = new JLabel("");
        lblGetCustID.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetCustID.setBounds(150, 170, 150, 25);
        add(lblGetCustID);

        lblGetSize = new JLabel("");
        lblGetSize.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetSize.setBounds(150, 220, 150, 25);
        add(lblGetSize);

        lblGetQTY = new JLabel("");
        lblGetQTY.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetQTY.setBounds(150, 270, 150, 25);
        add(lblGetQTY);

        lblGetAmount = new JLabel("");
        lblGetAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetAmount.setBounds(150, 320, 150, 25);
        add(lblGetAmount);

        lblGetStatus = new JLabel("");
        lblGetStatus.setFont(new Font("Arial", Font.PLAIN, 16));
        lblGetStatus.setBounds(150, 370, 150, 25);
        add(lblGetStatus);
    }
}
