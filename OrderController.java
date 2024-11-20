import java.io.*;

public class OrderController {

    public static boolean placeOrder(Order newOrder) throws IOException {
        if (newOrder!=null) {
            FileWriter fw = new FileWriter("OrdersDoc.txt", true);
            fw.write(newOrder.toString() + "\n");
            fw.close();
            return true;    
        }
        return false;
    }
}