public class FashionShop {
    public static void main(String[] args) {
        List ordersCollection = new List();
        new HomeWindow(ordersCollection).setVisible(true);
    }
}














































		// ---------------------- Write ----------------------

		// try {
		// FileWriter fw = new FileWriter("Customer.txt",true);
		// String line = c1.toString(write one by one);
		// fw.write(line + "\n");
		// fw.flush();
		// } catch (IOException ex) {

		// }

		// ---------------------- Print ----------------------

		// try {
		// BufferedReader br = new BufferedReader(new FileReader("Orders.txt"));
		// String line = br.readLine();
		// while (line != null) {
		// System.out.println(line);
		// line = br.readLine();
		// }
		// } catch (IOException ex) {

		// }

		// ---------------------- Load to List ----------------------

		// try {
		// BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));
		// String line = br.readLine();
		// while (line != null) {
		// String[] docRowData = line.split(",");
		// String orderId = docRowData[0];
		// String size = docRowData[1];
		// int quantity = Integer.parseInt(docRowData[2]);
		// double amount = Double.parseDouble(docRowData[3]);
		// String customerID= docRowData[4];
		// String orderStatus = docRowData[5];
		// Order order = new Order(orderId, size, quantity, amount, customerID,
		// orderStatus);
		// ordersCollection.add(order);
		// line = br.readLine();
		// }
		// } catch (IOException ex) {

		// }

		// ---------------------- Update ----------------------

		// for (int i = 0; i < ordersCollection.size(); i++) {
		// Order order = ordersCollection.get(i);
		// if (condition) {

		// }
		// }
		// try {
		// FileWriter fw = new FileWriter("Orders.txt");
		// for (int i = 0; i < ordersCollection.size(); i++) {
		// Order order = ordersCollection.get(i);
		// fw.write(order.getOrderId()+","+order.getSize()+","+order.getQuantity()+","+order.getAmount()+","+order.getCustomerID()+","+order.getOrderStatus());
		// }
		// fw.flush();
		// }catch (IOException ex) {

		// }
