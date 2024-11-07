import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class OrderController {
    private List ordersCollection;

    public OrderController(List ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    public void addToDoc(Order order) {
        try (FileWriter fw = new FileWriter("OrdersDoc.txt", true)) {
            String line = order.getOrderId() + "," +
                    order.getSize() + "," +
                    order.getQuantity() + "," +
                    order.getAmount() + "," +
                    order.getCustomerID() + "," +
                    order.getOrderStatus();
            fw.write(line + "\n");
            fw.flush();
            System.out.println("Order added to doc: " + line);
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
        }
    }

    public void loadToDoc() {
        ordersCollection.clear();

        try (BufferedReader br = new BufferedReader(new FileReader("OrdersDoc.txt"))) {
            String line = br.readLine();
            while (line != null) {
                String[] docRowData = line.split(",");
                try {
                    String orderId = docRowData[0];
                    String size = docRowData[1];
                    int quantity = Integer.parseInt(docRowData[2]);
                    double amount = Double.parseDouble(docRowData[3]);
                    String customerID = docRowData[4];
                    String orderStatus = docRowData[5];
                    Order order = new Order(orderId, size, quantity, amount, customerID, orderStatus);
                    ordersCollection.add(order);
                    System.out.println("Order loaded: " + line);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error parsing order line: " + line + " - " + e.getMessage());
                }
                line = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println("Error reading from file: " + ex.getMessage());
        }
    }
}
