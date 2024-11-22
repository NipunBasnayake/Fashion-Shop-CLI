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
            Order searchOrder = new Order(rowData[0], rowData[1], Integer.parseInt(rowData[2]),
                    Double.parseDouble(rowData[3]), rowData[4], rowData[5]);
            return searchOrder;
        } else {
            return null;
        }
    }

    public static List viewCustomers() throws IOException {
        List orderList = new List();
        try (BufferedReader br = new BufferedReader(new FileReader("OrdersDoc.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(",");
                try {
                    Order newOrder = new Order(
                        rowData[0], 
                        rowData[1], 
                        Integer.parseInt(rowData[2]), 
                        Double.parseDouble(rowData[3]), 
                        rowData[4], 
                        rowData[5]
                    );
                    orderList.add(newOrder);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Invalid data format in line: " + line);
                }
            }
        }
        return orderList;
    }
    
    public static boolean updateOrderStatus(Order order)throws IOException{
		List orderList=new List();
		Scanner input=new Scanner(new File("OrdersDoc.txt"));
		while(input.hasNext()){
			String line=input.nextLine();
			String[] rowData=line.split(",");
			Order c1=new Order(rowData[0], rowData[1], Integer.parseInt(rowData[2]),Double.parseDouble(rowData[3]), rowData[4], rowData[5]);
			orderList.add(c1);		
		}					
		int index=orderList.searchByOrder(order);
		if(index!=-1){
			boolean isUpdate=orderList.set(index,order);
			FileWriter fw=new FileWriter("OrdersDoc.txt");
			for(int i=0; i<orderList.size(); i++){
				Order c1=orderList.get(i);
				fw.write(c1.toString()+"\n");	
			}
			fw.close();
			return isUpdate;
		}
		return false;
	}

    public static boolean deleteOrder(Order order) throws IOException {
        List orderList = new List();
        boolean isDeleted = false;
    
        try (BufferedReader br = new BufferedReader(new FileReader("OrdersDoc.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(",");
                try {
                    Order odr = new Order(
                        rowData[0], 
                        rowData[1], 
                        Integer.parseInt(rowData[2]), 
                        Double.parseDouble(rowData[3]), 
                        rowData[4], 
                        rowData[5]
                    );
                    orderList.add(odr);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
        }
        isDeleted = orderList.remove(order);
        if (isDeleted) {
            try (FileWriter fw = new FileWriter("OrdersDoc.txt")) {
                for (int i = 0; i < orderList.size(); i++) {
                    Order odr = orderList.get(i);
                    fw.write(odr.toString() + "\n");
                }
            }
        }
        return isDeleted;
    }
    

}