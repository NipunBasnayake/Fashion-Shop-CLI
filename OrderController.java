import java.io.*;
import java.util.*;

public class OrderController {

    public static boolean placeOrder(Order newOrder) throws IOException {
        if (newOrder != null) {
            FileWriter fw = new FileWriter("OrdersDoc.txt", true);
            fw.write(newOrder.toString() + "\n");
            fw.close();
            return true;
        }
        return false;
    }

    public static List searchCustomerID(String customerId) throws IOException {
        List newList = new List();
        Scanner input = new Scanner(new File("OrdersDoc.txt"));
        while (input.hasNext()) {
            String line = input.nextLine();
            String[] rowData = line.split(",");

            Order newOrder = new Order(rowData[0], rowData[1], Integer.parseInt(rowData[2]),
                    Double.parseDouble(rowData[3]), rowData[4], rowData[5]);

            if (newOrder.getCustomerID().equals(customerId)) {
                newList.add(newOrder);
                return newList;
            }
        }
        return null;
    }

    public static Order searchOrderId(String orderId) throws IOException {
        String newLine = null;
        BufferedReader br = new BufferedReader(new FileReader("OrdersDoc.txt"));
        String line = br.readLine();

        while (line != null) {
            String orderID = line.substring(0, 9);
            if (orderID.equalsIgnoreCase(orderId)) {
                newLine = line;
                break;
            }
            line = br.readLine();
        }
        if (newLine != null) {
            String[] rowData = newLine.split(",");
            Order searchOrder = new Order(rowData[0], rowData[1], Integer.parseInt(rowData[2]), Double.parseDouble(rowData[3]), rowData[4], rowData[5]);
            return searchOrder;
        } else {
            return null;
        }
    }

    public static List viewCustomes() throws IOException{
        List orderList = new List();
        Scanner input = new Scanner(new File("OrdersDoc.txt"));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] rowData = line.split(",");
            Order newOrder = new Order(rowData[0], rowData[1], Integer.parseInt(rowData[2]), Double.parseDouble(rowData[3]), rowData[4], rowData[5]);
            orderList.add(newOrder);
        }
        return orderList;



        
    }





}