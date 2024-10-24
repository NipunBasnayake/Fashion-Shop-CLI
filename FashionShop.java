import java.util.*;

class Orders {
    private String orderId;
    private String size;
    private int quantity;
    private double amount;
    private String tpNumber;
    private String status;

	public Orders(){

	}

    public Orders(String orderId, String size, int quantity, double amount, String tpNumber, String status) {
        this.orderId = orderId;
        this.size = size;
        this.quantity = quantity;
        this.amount = amount;
        this.tpNumber = tpNumber;
        this.status = status;
    }

	// Getters
    public String getOrderId() {
        return orderId;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }

    public String getTpNumber() {
        return tpNumber;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTpNumber(String tpNumber) {
        this.tpNumber = tpNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

class FashionShop {
	static int orderNumber = 1;
	static String tShirtSize = "";
	static int qty = 0;
	static double amount = 0.0;
	static String cusPhoneNumber = "";
	static final int PROCESSING = 0;
	static final int DELIVERING = 1;
	static final int DELIVERED = 2;

	static String[] orderIdMainArray = new String[0];
	static String[] sizeMainArray = new String[0];
	static int[] qtyMainArray = new int[0];
	static double[] amountMainArray = new double[0];
	static String[] tpNumberMainArray = new String[0];
	static String[] statusMainArray = new String[0];

	static Orders[] ordersMainArray = new Orders[0];

	public static void main(String args[]) {
		homePage();
	}

	public static void homePage() {
		clearConsole();
		System.out.println("\n   /$$$$$$$$                 /$$       /$$                            /$$$$$$  /$$                          ");
		System.out.println("  | $$_____/                | $$      |__/                           /$$__  $$| $$                          ");
		System.out.println("  | $$    /$$$$$$   /$$$$$$$| $$$$$$$  /$$  /$$$$$$  /$$$$$$$       | $$  \\__/| $$$$$$$   /$$$$$$   /$$$$$$");
		System.out.println("  | $$$$$|____  $$ /$$_____/| $$__  $$| $$ /$$__  $$| $$__  $$      |  $$$$$$ | $$__  $$ /$$__  $$ /$$__  $$");
		System.out.println("  | $$__/ /$$$$$$$|  $$$$$$ | $$  \\ $$| $$| $$  \\ $$| $$  \\ $$       \\____  $$| $$  \\ $$| $$  \\ $$| $$  \\ $$");
		System.out.println("  | $$   /$$__  $$ \\____  $$| $$  | $$| $$| $$  | $$| $$  | $$       /$$  \\ $$| $$  | $$| $$  | $$| $$  | $$");
		System.out.println("  | $$  |  $$$$$$$ /$$$$$$$/| $$  | $$| $$|  $$$$$$/| $$  | $$      |  $$$$$$/| $$  | $$|  $$$$$$/| $$$$$$$/");
		System.out.println("  |__/   \\_______/|_______/ |__/  |__/|__/ \\______/ |__/  |__/       \\______/ |__/  |__/ \\______/ | $$____/ ");
		System.out.println("                                                                                                  | $$      ");
		System.out.println("                                                                                                  | $$      ");
		System.out.println("                                                                                                  |__/      ");
		System.out.println("\n------------------------------------------------------------------------------------------------------------\n\n");

		System.out.println("\t\t[1] Place Order\t\t\t\t\t[2] Search Customer\n\n");
		System.out.println("\t\t[3] Search Order\t\t\t\t[4] View Reports\n\n");
		System.out.println("\t\t[5] Set Order Status\t\t\t\t[6] Delete Order\n\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("\n\t\tEnter Option : ");
		int homeOption = scanner.nextInt();

		switch (homeOption) {
			case 1:
				placeOrder();
				break;
			case 2:
				searchCustomer();
				break;
			case 3:
				searchOrder();
				break;
			case 4:
				reports();
				break;
			case 5:
				orderStatus();
				break;
			case 6:
				deleteOrder();
				break;
			default:
				homePage();
				break;
		}
	}

	public static void placeOrder() {
		clearConsole();
		System.out.println("   _____  _                   ____          _           ");
		System.out.println("  |  __ \\| |                 / __ \\        | |          ");
		System.out.println("  | |__) | | __ _  ___ ___  | |  | |_ __ __| | ___ _ __ ");
		System.out.println("  |  ___/| |/ _` |/ __/ _ \\ | |  | | '__/ _` |/ _ \\ '__|");
		System.out.println("  | |    | | (_| | (_|  __/ | |__| | | | (_| |  __/ |   ");
		System.out.println("  |_|    |_|\\__,_|\\___\\___|  \\____/|_|  \\__,_|\\___|_|   ");
		System.out.println("\n---------------------------------------------------------\n\n");

		Scanner scanner = new Scanner(System.in);

		String orderID = generateOrderID();
		System.out.println("Order ID: " + orderID);
		getPhoneNumber();
		getSize();
		getQty();

		switch (tShirtSize) {
			case "XS":
				amount = qty * 600.00;
				break;
			case "S":
				amount = qty * 800.00;
				break;
			case "M":
				amount = qty * 900.00;
				break;
			case "L":
				amount = qty * 1000.00;
				break;
			case "XL":
				amount = qty * 1100.00;
				break;
			case "XXL":
				amount = qty * 1200.00;
				break;
		}
		System.out.println("\nAmount : " + amount);
		confirmOrder();
		orderNumber += 1;

		System.out.print("\n\nDo you want to place another order? (y/n) : ");
		char config = scanner.next().charAt(0);
		reDirection(config, "placeOrderConfig");
	}

	public static void confirmOrder() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\n\nDo you want to place this order? (y/n) : ");
		char configPlace = scanner.next().charAt(0);

		if (configPlace == 'Y' || configPlace == 'y') {
			System.out.print("\n\tOrder Placed..!");
			extendArrays();
		} else if (configPlace == 'N' || configPlace == 'n') {
			placeOrder();
		} else {
			System.out.print("\033[3A");
			System.out.print("\033[0J");
			confirmOrder();
		}
	}

	public static void extendArrays() {
		Orders[] tempOrders = new Orders[ordersMainArray.length + 1];

		for (int i = 0; i < ordersMainArray.length; i++) {
			tempOrders[i] = ordersMainArray[i];
		}

		Orders newOrder = new Orders();

		newOrder.setOrderId(generateOrderID());
		newOrder.setSize(tShirtSize);
		newOrder.setQuantity(qty);
		newOrder.setAmount(amount);
		newOrder.setTpNumber(cusPhoneNumber);
		newOrder.setStatus(statusCheck(PROCESSING));

		tempOrders[tempOrders.length - 1] = newOrder;
		ordersMainArray = tempOrders;

		// System.out.println(ordersMainArray[0].getOrderId());
		// System.out.println(ordersMainArray[0].getSize());
		// System.out.println(ordersMainArray[0].getQuantity());
		// System.out.println(ordersMainArray[0].getAmount());
		// System.out.println(ordersMainArray[0].getTpNumber());
		// System.out.println(ordersMainArray[0].getStatus());
	}

	public static void searchCustomer() {
		clearConsole();
		System.out.println("\n    _____                     _        _____          _                           ");
		System.out.println("   / ____|                   | |      / ____|        | |                          ");
		System.out.println("  | (___   ___  __ _ _ __ ___| |__   | |    _   _ ___| |_ ___  _ __ ___   ___ _ __");
		System.out.println("   \\___ \\ / _ \\/ _` | '__/ __| '_ \\  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__|");
		System.out.println("   ____) |  __/ (_| | | | (__| | | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  ");
		System.out.println("  |_____/ \\___|\\__,_|_|  \\___|_| |_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  ");
		System.out.println("\n-------------------------------------------------------------------------------------\n\n");

		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer Phone Number: ");
        String searchInputTP = scanner.nextLine();

        boolean found = false;
        double totalAmount = 0;

        String[] processedSizes = new String[ordersMainArray.length];
        int[] totalQtyForSize = new int[ordersMainArray.length];
        double[] totalAmountForSize = new double[ordersMainArray.length];
        int processedCount = 0;

        for (int i = 0; i < ordersMainArray.length; i++) {
            if (ordersMainArray[i].getTpNumber().equals(searchInputTP)) {
                if (!found) {
                    System.out.println("\n\t+---------------+---------------+---------------+");
                    System.out.println("\t|\tSize\t|\tQTY\t|\tAmount\t|");
                    System.out.println("\t+---------------+---------------+---------------+");
					System.out.println("\t|               |               |               |");
                    found = true;
                }
                boolean sizeAlreadyProcessed = false;
                int index = -1;

                for (int k = 0; k < processedCount; k++) {
                    if (processedSizes[k].equals(ordersMainArray[i].getSize())) {
                        sizeAlreadyProcessed = true;
                        index = k;
                        break;
                    }
                }
                if (sizeAlreadyProcessed) {
                    totalQtyForSize[index] += ordersMainArray[i].getQuantity();
                    totalAmountForSize[index] += ordersMainArray[i].getAmount();
                } else {
                    processedSizes[processedCount] = ordersMainArray[i].getSize();
                    totalQtyForSize[processedCount] = ordersMainArray[i].getQuantity();
                    totalAmountForSize[processedCount] = ordersMainArray[i].getAmount();
                    processedCount++;
                }
                totalAmount += ordersMainArray[i].getAmount();
            }
        }
		
        for (int i = 0; i < processedCount - 1; i++) {
            for (int j = 0; j < processedCount - i - 1; j++) {
                if (totalAmountForSize[j] < totalAmountForSize[j + 1]) {

                    String tempSize = processedSizes[j];
                    processedSizes[j] = processedSizes[j + 1];
                    processedSizes[j + 1] = tempSize;

                    int tempQty = totalQtyForSize[j];
                    totalQtyForSize[j] = totalQtyForSize[j + 1];
                    totalQtyForSize[j + 1] = tempQty;

                    double tempAmount = totalAmountForSize[j];
                    totalAmountForSize[j] = totalAmountForSize[j + 1];
                    totalAmountForSize[j + 1] = tempAmount;
                }
            }
        }
		
		for (int i = 0; i < processedCount; i++) {
			System.out.println("\t|\t" + processedSizes[i] + "\t|\t" + totalQtyForSize[i] + "\t|\t" + totalAmountForSize[i] + "\t|");
			System.out.println("\t|               |               |               |");
		}
		if (found) {
			System.out.println("\t+---------------+---------------+---------------+");
		} else {
			System.out.println("\n\tInvalid input..");
		}
		System.out.print("\nDo you want to search another customer report (y/n) : ");
		String userChoice = scanner.nextLine();
		if (userChoice.equalsIgnoreCase("y")) {
			searchCustomer();
		} else if (userChoice.equalsIgnoreCase("n")) {
			homePage();
		}
	}

	public static void searchOrder() {
		clearConsole();
		System.out.println("    _____                     _        ____          _           ");
		System.out.println("   / ____|                   | |      / __ \\        | |          ");
		System.out.println("  | (___   ___  __ _ _ __ ___| |__   | |  | |_ __ __| | ___ _ __ ");
		System.out.println("   \\___ \\ / _ \\/ _` | '__/ __| '_ \\  | |  | | '__/ _` |/ _ \\ '__|");
		System.out.println("   ____) |  __/ (_| | | | (__| | | | | |__| | | | (_| |  __/ |   ");
		System.out.println("  |_____/ \\___|\\__,_|_|  \\___|_| |_|  \\____/|_|  \\__,_|\\___|_|   ");
		System.out.println("\n-------------------------------------------------------------------\n\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Order ID: ");
		String searchInputOID = scanner.nextLine();

		boolean found = false;

		for (int i = 0; i < orderIdMainArray.length; i++) {
			if (orderIdMainArray[i].equals(searchInputOID)) {
				found = true;
				System.out.println("\nPhone Number : " + tpNumberMainArray[i]);
				System.out.println("Size         : " + sizeMainArray[i]);
				System.out.println("QTY          : " + qtyMainArray[i]);
				System.out.println("Amount       : " + amountMainArray[i]);
				System.out.println("Status       : " + statusMainArray[i]);
				break;
			}
		}
		if (!found) {
			System.out.println("\n\tInvalid Order ID..");
			System.out.print("\n\nDo you want to search another order? (y/n) : ");
			char config = scanner.next().charAt(0);
			reDirection(config, "searchOrder");
		}
		System.out.print("\n\nDo you want to search another order? (y/n) : ");
		char config = scanner.next().charAt(0);
		reDirection(config, "searchOrder");
	}

	public static void reports() {
		clearConsole();
		System.out.println("   _____                       _       ");
		System.out.println("  |  __ \\                     | |      ");
		System.out.println("  | |__) |___ _ __   ___  _ __| |_ ___ ");
		System.out.println("  |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|");
		System.out.println("  | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\");
		System.out.println("  |_|  \\_\\___| .__/ \\___/|_|   \\__|___/");
		System.out.println("             | |                         ");
		System.out.println("             |_|                         ");
		System.out.println("-----------------------------------------\n\n");

		System.out.println("\t[1] Customer Reports\n");
		System.out.println("\t[2] Item Reports\n");
		System.out.println("\t[3] Order Reports\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter Option : ");
		int reportsOption = scanner.nextInt();

		switch (reportsOption) {
			case 1:
				customerReports();
				break;
			case 2:
				itemReports();
				break;
			case 3:
				orderReports();
				break;
			default:
				reports();
				break;
		}
	}

	public static void customerReports() {
		clearConsole();
		System.out.println("    _____          _                              _____                       _       ");
		System.out.println("   / ____|        | |                            |  __ \\                     | |      ");
		System.out.println("  | |    _   _ ___| |_ ___  _ __ ___   ___ _ __  | |__) |___ _ __   ___  _ __| |_ ___ ");
		System.out.println("  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__| |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|");
		System.out.println("  | |___| |_| \\__ \\ || (_) | | | | | |  __/ |    | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\");
		System.out.println("   \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|    |_|  \\_\\___| .__/ \\___/|_|   \\__|___/");
		System.out.println("                                                            | |                       ");
		System.out.println("                                                            |_|                       ");
		System.out.println("----------------------------------------------------------------------------------------\n\n");

		System.out.println("\t[1] Best in Customers\n");
		System.out.println("\t[2] View Customers\n");
		System.out.println("\t[3] All Customer Reports\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter Option : ");
		int CustomerReportsOption = scanner.nextInt();

		switch (CustomerReportsOption) {
			case 1:
				bestInCustomers();
				break;
			case 2:
				viewCustomers();
				break;
			case 3:
				allCustomerReports();
				break;
			default:
				customerReports();
				break;
		}
	}

	public static void bestInCustomers() {
		clearConsole();

		System.out.println("   ____            _     _____          _____          _                                ");
		System.out.println("  |  _ \\          | |   |_   _|        / ____|        | |                               ");
		System.out.println("  | |_) | ___  ___| |_    | |  _ __   | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___ ");
		System.out.println("  |  _ < / _ \\/ __| __|   | | | '_ \\  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__/ __|");
		System.out.println("  | |_) |  __/\\__ \\ |_   _| |_| | | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\");
		System.out.println("  |____/ \\___||___/\\__| |_____|_| |_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/");
		System.out.println("----------------------------------------------------------------------------------------\n");


		String[][] customerData = new String[tpNumberMainArray.length][3];
		int uniqueCustomers = 0;

		for (int i = 0; i < tpNumberMainArray.length; i++) {
			boolean isNewCustomer = true;
			int customerIndex = -1;
			
			for (int j = 0; j < uniqueCustomers; j++) {
				if (tpNumberMainArray[i].equals(customerData[j][0])) {
					isNewCustomer = false;
					customerIndex = j;
					break;
				}
			}
			if (isNewCustomer) {
				customerData[uniqueCustomers][0] = tpNumberMainArray[i];
				customerData[uniqueCustomers][1] = String.valueOf(qtyMainArray[i]);
				customerData[uniqueCustomers][2] = String.valueOf(amountMainArray[i]);
				uniqueCustomers++;
			} else {
				customerData[customerIndex][1] = String.valueOf(Integer.parseInt(customerData[customerIndex][1]) + qtyMainArray[i]);
				customerData[customerIndex][2] = String.valueOf(Double.parseDouble(customerData[customerIndex][2]) + amountMainArray[i]);
			}
		}
		for (int i = 0; i < uniqueCustomers - 1; i++) {
			for (int j = 0; j < uniqueCustomers - i - 1; j++) {
				if (Double.parseDouble(customerData[j][2]) < Double.parseDouble(customerData[j + 1][2])) {
					String[] temp = customerData[j];
					customerData[j] = customerData[j + 1];
					customerData[j + 1] = temp;
				}
			}
		}
		System.out.printf("\t+---------------+---------------+---------------+\n");
		System.out.printf("\t| %-13s | %-13s | %-13s |\n", "Customer ID", "All QTY", "Total Amount");
		System.out.printf("\t+---------------+---------------+---------------+\n");

		for (int i = 0; i < uniqueCustomers; i++) {
			System.out.printf("\t|               |               |               |\n");
			System.out.printf("\t| %-13s | %-13s | %-13s |\n", customerData[i][0], customerData[i][1], customerData[i][2]);
		}
		System.out.printf("\t+---------------+---------------+---------------+\n");
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nTo access the main Menu, please enter 0 : ");
		int toMenuInput = scanner.nextInt();

		if (toMenuInput == 0) {
			homePage();
		} else {
			bestInCustomers();
		}

	}

	public static void viewCustomers() {
		clearConsole();
		System.out.println("  __      ___                  _____          _                                ");
		System.out.println("  \\ \\    / (_)                / ____|        | |                               ");
		System.out.println("   \\ \\  / / _  _____      __ | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___ ");
		System.out.println("    \\ \\/ / | |/ _ \\ \\ /\\ / / | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__/ __|");
		System.out.println("     \\  /  | |  __/\\ V  V /  | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\");
		System.out.println("      \\/   |_|\\___| \\_/\\_/    \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/");
		System.out.println("-----------------------------------------------------------------------------------\n");

		String[][] customerData = new String[tpNumberMainArray.length][3];
		int uniqueCustomers = 0;

		for (int i = 0; i < tpNumberMainArray.length; i++) {
			boolean isNewCustomer = true;
			int customerIndex = -1;

			for (int j = 0; j < uniqueCustomers; j++) {
				if (tpNumberMainArray[i].equals(customerData[j][0])) {
					isNewCustomer = false;
					customerIndex = j;
					break;
				}
			}
			if (isNewCustomer) {
				customerData[uniqueCustomers][0] = tpNumberMainArray[i];
				customerData[uniqueCustomers][1] = String.valueOf(qtyMainArray[i]);
				customerData[uniqueCustomers][2] = String.valueOf(amountMainArray[i]);
				uniqueCustomers++;
			} else {
				customerData[customerIndex][1] = String.valueOf(Integer.parseInt(customerData[customerIndex][1]) + qtyMainArray[i]);
				customerData[customerIndex][2] = String.valueOf(Double.parseDouble(customerData[customerIndex][2]) + amountMainArray[i]);
			}
		}
		System.out.printf("\t+---------------+---------------+---------------+\n");
		System.out.printf("\t| %-13s | %-13s | %-13s |\n", "Customer ID", "All QTY", "Total Amount");
		System.out.printf("\t+---------------+---------------+---------------+\n");

		for (int i = 0; i < uniqueCustomers; i++) {
			System.out.printf("\t|               |               |               |\n");
			System.out.printf("\t| %-13s | %-13s | %-13s |\n", customerData[i][0], customerData[i][1], customerData[i][2]);
		}
		System.out.printf("\t+---------------+---------------+---------------+\n");
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nTo access the main Menu, please enter 0 : ");
		int toMenuInput = scanner.nextInt();

		if (toMenuInput == 0) {
			homePage();
		} else {
			viewCustomers();
		}
	}

	public static void allCustomerReports() {
		clearConsole();
		System.out.println("            _ _    _____          _                                  _____                       _       ");
		System.out.println("      /\\   | | |  / ____|        | |                                |  __ \\                     | |      ");
		System.out.println("     /  \\  | | | | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___  | |__) |___ _ __   ___  _ __| |_ ___ ");
		System.out.println("    / /\\ \\ | | | | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__/ __| |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|");
		System.out.println("   / ____ \\| | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\ | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\");
		System.out.println("  /_/    \\_\\_|_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/ |_|  \\_\\___| .__/ \\___/|_|   \\__|___/");
		System.out.println("                                                                                | |                      ");
		System.out.println("                                                                                |_|                      ");
		System.out.println("-------------------------------------------------------------------------------------------------------------\n");

		String[][] customerData = new String[tpNumberMainArray.length][8];
		int uniqueCustomers = 0;
		
		for (int i = 0; i < tpNumberMainArray.length; i++) {
			boolean isNewCustomer = true;
			int customerIndex = -1;

			for (int j = 0; j < uniqueCustomers; j++) {
				if (tpNumberMainArray[i].equals(customerData[j][0])) {
					isNewCustomer = false;
					customerIndex = j;
					break;
				}
			}

			if (isNewCustomer) {
				customerData[uniqueCustomers][0] = tpNumberMainArray[i];
				for (int k = 1; k <= 6; k++) {
					customerData[uniqueCustomers][k] = "0";
				}
				customerData[uniqueCustomers][7] = "0.0";
				customerIndex = uniqueCustomers;
				uniqueCustomers++;
			}

			if (sizeMainArray[i].equalsIgnoreCase("XS")) {
				customerData[customerIndex][1] = Integer.toString(Integer.parseInt(customerData[customerIndex][1]) + qtyMainArray[i]); 
			} else if (sizeMainArray[i].equalsIgnoreCase("S")) {
				customerData[customerIndex][2] = Integer.toString(Integer.parseInt(customerData[customerIndex][2]) + qtyMainArray[i]);
			} else if (sizeMainArray[i].equalsIgnoreCase("M")) {
				customerData[customerIndex][3] = Integer.toString(Integer.parseInt(customerData[customerIndex][3]) + qtyMainArray[i]);
			} else if (sizeMainArray[i].equalsIgnoreCase("L")) {
				customerData[customerIndex][4] = Integer.toString(Integer.parseInt(customerData[customerIndex][4]) + qtyMainArray[i]);
			} else if (sizeMainArray[i].equalsIgnoreCase("XL")) {
				customerData[customerIndex][5] = Integer.toString(Integer.parseInt(customerData[customerIndex][5]) + qtyMainArray[i]);
			} else if (sizeMainArray[i].equalsIgnoreCase("XXL")) {
				customerData[customerIndex][6] = Integer.toString(Integer.parseInt(customerData[customerIndex][6]) + qtyMainArray[i]);
			}			

			customerData[customerIndex][7] = Double.toString(Double.parseDouble(customerData[customerIndex][7]) + amountMainArray[i]);
		}
		

		System.out.printf("\t+---------------+----+----+----+----+----+-----+---------------+\n");
		System.out.printf("\t| Phone Number  | XS | S  | M  | L  | XL | XXL |  Total Amount |\n");
		System.out.printf("\t+---------------+----+----+----+----+----+-----+---------------+\n");

		for (int i = 0; i < uniqueCustomers; i++) {
			System.out.printf("\t|               |    |    |    |    |    |     |               |\n");
			System.out.printf("\t| %-13s | %-2s | %-2s | %-2s | %-2s | %-2s | %-3s |   %-12.2f|\n",
				customerData[i][0], customerData[i][1], customerData[i][2], customerData[i][3], 
				customerData[i][4], customerData[i][5], customerData[i][6], Double.parseDouble(customerData[i][7]));
		}

		System.out.printf("\t+---------------+----+----+----+----+----+-----+---------------+\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("\nTo access the main Menu, please enter 0 : ");
		int toMenuInput = scanner.nextInt();

		if (toMenuInput == 0) {
			homePage();
		} else {
			allCustomerReports();
		}

	}

	public static void itemReports() {
		clearConsole();
		System.out.println("   _____ _                   _____                       _       ");
		System.out.println("  |_   _| |                 |  __ \\                     | |      ");
		System.out.println("    | | | |_ ___ _ __ ___   | |__) |___ _ __   ___  _ __| |_ ___ ");
		System.out.println("    | | | __/ _ \\ '_ ` _ \\  |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|");
		System.out.println("   _| |_| ||  __/ | | | | | | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\");
		System.out.println("  |_____|\\__\\___|_| |_| |_| |_|  \\_\\___| .__/ \\___/|_|   \\__|___/");
		System.out.println("                                       | |                      ");
		System.out.println("                                       |_|                      ");
		System.out.println("---------------------------------------------------------------------\n\n");

		System.out.println("\t[1] Best Selling Categories Sorted by QTY\n");
		System.out.println("\t[2] Best Selling Categories Sorted by Amount\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter Option : ");
		int ItemReportsOption = scanner.nextInt();

		switch (ItemReportsOption) {
			case 1:
				sortedByQTY();
				break;
			case 2:
				sortedByAmount();
				break;
			default:
				itemReports();
				break;
		}
	}

	public static void sortedByQTY() {
		clearConsole();
		System.out.println("    _____            _           _   ____           ____ _________     __");
		System.out.println("   / ____|          | |         | | |  _ \\         / __ \\__   __\\ \\   / /");
		System.out.println("  | (___   ___  _ __| |_ ___  __| | | |_) |_   _  | |  | | | |   \\ \\_/ / ");
		System.out.println("   \\___ \\ / _ \\| '__| __/ _ \\/ _` | |  _ <| | | | | |  | | | |    \\   /  ");
		System.out.println("   ____) | (_) | |  | ||  __/ (_| | | |_) | |_| | | |__| | | |     | |   ");
		System.out.println("  |_____/ \\___/|_|   \\__\\___|\\__,_| |____/ \\__, |  \\___\\_\\ |_|     |_|   ");
		System.out.println("                                            __/ |                        ");
		System.out.println("                                           |___/                         ");
		System.out.println("---------------------------------------------------------------------------\n");

		String[] uniqueSizes = new String[sizeMainArray.length];
		int[] totalQty = new int[sizeMainArray.length];
		double[] totalAmount = new double[sizeMainArray.length];
		int uniqueSizesCount = 0;

		for (int i = 0; i < sizeMainArray.length; i++) {
			boolean isNewSize = true;
			int sizeIndex = -1;

			for (int j = 0; j < uniqueSizesCount; j++) {
				if (sizeMainArray[i].equals(uniqueSizes[j])) {
					isNewSize = false;
					sizeIndex = j;
					break;
				}
			}

			if (isNewSize) {
				uniqueSizes[uniqueSizesCount] = sizeMainArray[i];
				totalQty[uniqueSizesCount] = qtyMainArray[i];
				totalAmount[uniqueSizesCount] = amountMainArray[i];
				uniqueSizesCount++;
			} else {
				totalQty[sizeIndex] += qtyMainArray[i];
				totalAmount[sizeIndex] += amountMainArray[i];
			}
		}

		for (int i = 0; i < uniqueSizesCount - 1; i++) {
			for (int j = 0; j < uniqueSizesCount - i - 1; j++) {

				if (totalQty[j] < totalQty[j + 1]) {

					String tempSize = uniqueSizes[j];
					uniqueSizes[j] = uniqueSizes[j + 1];
					uniqueSizes[j + 1] = tempSize;

					int tempQty = totalQty[j];
					totalQty[j] = totalQty[j + 1];
					totalQty[j + 1] = tempQty;

					double tempAmount = totalAmount[j];
					totalAmount[j] = totalAmount[j + 1];
					totalAmount[j + 1] = tempAmount;
				}
			}
		}

		System.out.printf("\n\t+---------------+---------------+---------------+\n");
		System.out.printf("\t| %-13s | %-13s | %-13s |\n", "Size", "Total QTY", "Total Amount");
		System.out.printf("\t+---------------+---------------+---------------+\n");

		for (int i = 0; i < uniqueSizesCount; i++) {
			System.out.printf("\t|               |               |               |\n");
			System.out.printf("\t| %-13s | %-13d | %-13.2f |\n", uniqueSizes[i], totalQty[i], totalAmount[i]);
		}
		System.out.printf("\t+---------------+---------------+---------------+\n");
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nTo access the main Menu, please enter 0 : ");
		int toMenuInput = scanner.nextInt();

		if (toMenuInput == 0) {
			homePage();
		} else {
			System.out.println("Invalid input..");
			sortedByQTY();
		}
	}

	public static void sortedByAmount() {
		clearConsole();
		System.out.println("    _____            _           _   ____                                               _   ");
		System.out.println("   / ____|          | |         | | |  _ \\            /\\                               | |  ");
		System.out.println("  | (___   ___  _ __| |_ ___  __| | | |_) |_   _     /  \\   _ __ ___   ___  _   _ _ __ | |_ ");
		System.out.println("   \\___ \\ / _ \\| '__| __/ _ \\/ _` | |  _ <| | | |   / /\\ \\ | '_ ` _ \\ / _ \\| | | | '_ \\| __|");
		System.out.println("   ____) | (_) | |  | ||  __/ (_| | | |_) | |_| |  / ____ \\| | | | | | (_) | |_| | | | | |_ ");
		System.out.println("  |_____/ \\___/|_|   \\__\\___|\\__,_| |____/ \\__, | /_/    \\_\\_| |_| |_|\\___/ \\__,_|_| |_|\\__|");
		System.out.println("                                            __/ |                                           ");
		System.out.println("                                           |___/                                            ");
		System.out.println("----------------------------------------------------------------------------------------------\n");

		String[] uniqueSizes = new String[sizeMainArray.length];
		int[] totalQty = new int[sizeMainArray.length];
		double[] totalAmount = new double[sizeMainArray.length];
		int uniqueSizesCount = 0;

		for (int i = 0; i < sizeMainArray.length; i++) {
			boolean isNewSize = true;
			int sizeIndex = -1;

			for (int j = 0; j < uniqueSizesCount; j++) {
				if (sizeMainArray[i].equals(uniqueSizes[j])) {
					isNewSize = false;
					sizeIndex = j;
					break;
				}
			}
			if (isNewSize) {
				uniqueSizes[uniqueSizesCount] = sizeMainArray[i];
				totalQty[uniqueSizesCount] = qtyMainArray[i];
				totalAmount[uniqueSizesCount] = amountMainArray[i];
				uniqueSizesCount++;
			} else {
				totalQty[sizeIndex] += qtyMainArray[i];
				totalAmount[sizeIndex] += amountMainArray[i];
			}
		}

		for (int i = 0; i < uniqueSizesCount - 1; i++) {
			for (int j = 0; j < uniqueSizesCount - i - 1; j++) {
				if (totalAmount[j] < totalAmount[j + 1]) {

					String tempSize = uniqueSizes[j];
					uniqueSizes[j] = uniqueSizes[j + 1];
					uniqueSizes[j + 1] = tempSize;

					int tempQty = totalQty[j];
					totalQty[j] = totalQty[j + 1];
					totalQty[j + 1] = tempQty;

					double tempAmount = totalAmount[j];
					totalAmount[j] = totalAmount[j + 1];
					totalAmount[j + 1] = tempAmount;
				}
			}
		}

		System.out.printf("\n\t+---------------+---------------+---------------+\n");
		System.out.printf("\t| %-13s | %-13s | %-13s |\n", "Size", "Total QTY", "Total Amount");
		System.out.printf("\t+---------------+---------------+---------------+\n");

		for (int i = 0; i < uniqueSizesCount; i++) {
			System.out.printf("\t|               |               |               |\n");
			System.out.printf("\t| %-13s | %-13d | %-13.2f |\n", uniqueSizes[i], totalQty[i], totalAmount[i]);
		}
		System.out.printf("\t+---------------+---------------+---------------+\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("\nTo access the main Menu, please enter 0 : ");
		int toMenuInput = scanner.nextInt();

		if (toMenuInput == 0) {
			homePage();
		} else {
			System.out.println("Invalid input..");
			sortedByAmount();
		}
	}

	public static void orderReports() {
		clearConsole();
		System.out.println("    ____          _             _____                       _       ");
		System.out.println("   / __ \\        | |           |  __ \\                     | |      ");
		System.out.println("  | |  | |_ __ __| | ___ _ __  | |__) |___ _ __   ___  _ __| |_ ___ ");
		System.out.println("  | |  | | '__/ _` |/ _ \\ '__| |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|");
		System.out.println("  | |__| | | | (_| |  __/ |    | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\");
		System.out.println("   \\____/|_|  \\__,_|\\___|_|    |_|  \\_\\___| .__/ \\___/|_|   \\__|___/");
		System.out.println("                                          | |                       ");
		System.out.println("                                          |_|                       ");
		System.out.println("-------------------------------------------------------------------------\n\n");

		System.out.println("\t[1] All Orders\n");
		System.out.println("\t[2] Orders By Amount\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter Option : ");
		int OrderReportsOption = scanner.nextInt();

		switch (OrderReportsOption) {
			case 1:
				viewOrders();
				break;
			case 2:
				ordersByAmount();
				break;
			default:
				orderReports();
				break;
		}
	}

	public static void viewOrders() {
		clearConsole();
		System.out.println("  __      ___                  ____          _               ");
		System.out.println("  \\ \\    / (_)                / __ \\        | |              ");
		System.out.println("   \\ \\  / / _  _____      __ | |  | |_ __ __| | ___ _ __ ___ ");
		System.out.println("    \\ \\/ / | |/ _ \\ \\ /\\ / / | |  | | '__/ _` |/ _ \\ '__/ __|");
		System.out.println("     \\  /  | |  __/\\ V  V /  | |__| | | | (_| |  __/ |  \\__ \\");
		System.out.println("      \\/   |_|\\___| \\/\\_/     \\____/|_|  \\__,_|\\___|_|  |___/");
		System.out.println("---------------------------------------------------------------\n");
		System.out.printf("\n\t+---------------+---------------+---------------+---------------+---------------+---------------+\n");
		System.out.printf("\t| %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |\n", "Order ID", "Customer ID", "Size", "Quantity", "Amount", "Status");
		System.out.printf("\t+---------------+---------------+---------------+---------------+---------------+---------------+\n");

		for(int i=orderIdMainArray.length-1; i>=0; i--){
			System.out.printf("\t| %-13s | %-13s | %-13s | %-13d | %-13.2f | %-13s |\n",
					orderIdMainArray[i], tpNumberMainArray[i], sizeMainArray[i],
					qtyMainArray[i], amountMainArray[i], statusMainArray[i]);
		}

		System.out.printf("\t+---------------+---------------+---------------+---------------+---------------+---------------+\n");
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nTo access the main Menu, please enter 0: ");
		int toMenuInput = scanner.nextInt();

		if (toMenuInput == 0) {
			homePage();
		} else {
			System.out.println("Invalid input..");
			viewOrders();
		}
	}

	public static void ordersByAmount() {
		clearConsole();
		System.out.println("    ____          _                 ____                                               _   ");
		System.out.println("   / __ \\        | |               |  _ \\            /\\                               | |  ");
		System.out.println("  | |  | |_ __ __| | ___ _ __ ___  | |_) |_   _     /  \\   _ __ ___   ___  _   _ _ __ | |_ ");
		System.out.println("  | |  | | '__/ _` |/ _ \\ '__/ __| |  _ <| | | |   / /\\ \\ | '_ ` _ \\ / _ \\| | | | '_ \\| __|");
		System.out.println("  | |__| | | | (_| |  __/ |  \\__ \\ | |_) | |_| |  / ____ \\| | | | | | (_) | |_| | | | | |_ ");
		System.out.println("   \\____/|_|  \\__,_|\\___|_|  |___/ |____/ \\__, | /_/    \\_\\_| |_| |_|\\___/ \\__,_|_| |_|\\__|");
		System.out.println("                                           __/ |                                           ");
		System.out.println("                                          |___/                                            ");
		System.out.println("---------------------------------------------------------------------------------------------\n");

		for (int i = 0; i < amountMainArray.length - 1; i++) {
			for (int j = 0; j < amountMainArray.length - i - 1; j++) {
				if (amountMainArray[j] < amountMainArray[j + 1]) {

					double tempAmount = amountMainArray[j];
					amountMainArray[j] = amountMainArray[j + 1];
					amountMainArray[j + 1] = tempAmount;

					String tempOrderId = orderIdMainArray[j];
					orderIdMainArray[j] = orderIdMainArray[j + 1];
					orderIdMainArray[j + 1] = tempOrderId;

					String tempSize = sizeMainArray[j];
					sizeMainArray[j] = sizeMainArray[j + 1];
					sizeMainArray[j + 1] = tempSize;

					int tempQty = qtyMainArray[j];
					qtyMainArray[j] = qtyMainArray[j + 1];
					qtyMainArray[j + 1] = tempQty;

					String tempStatus = statusMainArray[j];
					statusMainArray[j] = statusMainArray[j + 1];
					statusMainArray[j + 1] = tempStatus;

					String tempCustomerId = tpNumberMainArray[j];
					tpNumberMainArray[j] = tpNumberMainArray[j + 1];
					tpNumberMainArray[j + 1] = tempCustomerId;
				}
			}
		}
		System.out.printf(
				"\n\t+---------------+---------------+---------------+---------------+---------------+---------------+\n");
		System.out.printf("\t| %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |\n", "Order ID", "Customer ID", "Size",
				"Quantity", "Amount", "Status");
		System.out.printf("\t+---------------+---------------+---------------+---------------+---------------+---------------+\n");

		for (int i = 0; i < orderIdMainArray.length; i++) {
			System.out.printf("\t| %-13s | %-13s | %-13s | %-13d | %-13.2f | %-13s |\n", orderIdMainArray[i],
					tpNumberMainArray[i], sizeMainArray[i], qtyMainArray[i], amountMainArray[i], statusMainArray[i]);
		}
		System.out.printf("\t+---------------+---------------+---------------+---------------+---------------+---------------+\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("\nTo access the main Menu, please enter 0: ");
		int toMenuInput = scanner.nextInt();

		if (toMenuInput == 0) {
			homePage();
		} else {
			System.out.println("Invalid input..");
			sortedByAmount();
		}
	}

	public static void orderStatus() {
		clearConsole();
		System.out.println("    ____          _              _____ _        _             ");
		System.out.println("   / __ \\        | |            / ____| |      | |            ");
		System.out.println("  | |  | |_ __ __| | ___ _ __  | (___ | |_ __ _| |_ _   _ ___ ");
		System.out.println("  | |  | | '__/ _` |/ _ \\ '__|  \\___ \\| __/ _` | __| | | / __|");
		System.out.println("  | |__| | | | (_| |  __/ |     ____) | || (_| | |_| |_| \\__ \\");
		System.out.println("   \\____/|_|  \\_,_|\\___|_|    |_____/ \\__\\__,_|\\__|\\__,_|___/");
		System.out.println("\n--------------------------------------------------------------\n\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Order ID: ");
		String sOrderStatusOID = scanner.nextLine();
		String status = "";
		int element = 0;

		for (int i = 0; i < orderIdMainArray.length; i++) {
			if (orderIdMainArray[i].equals(sOrderStatusOID)) {
				System.out.println("\nPhone Number : " + tpNumberMainArray[i]);
				System.out.println("Size         : " + sizeMainArray[i]);
				System.out.println("QTY          : " + qtyMainArray[i]);
				System.out.println("Amount       : " + amountMainArray[i]);
				element = i;
				status = statusMainArray[i];
				System.out.println("Status       : " + status);
				break;
			} else {
				System.out.print("\n\nDo you want to search another Order ID? (y/n) : ");
				char config = scanner.next().charAt(0);
				reDirection(config, "orderStatus");
			}
		}
		if (status.equalsIgnoreCase("processing")) {
			System.out.print("\n\nDo you want to change this order status? (y/n) : ");
			String confirmInput = scanner.nextLine();

			if (confirmInput.equalsIgnoreCase("Y")) {
				System.out.println("\n\t[1] Order Dilivering\n");
				System.out.println("\t[2] Order Dilivered\n");

				System.out.print("\nEnter Option : ");
				int processingOption = scanner.nextInt();

				switch (processingOption) {
					case 1:
						statusMainArray[element] = "Delivering";
						break;
					case 2:
						statusMainArray[element] = "Delivered";
						break;
					default:
						System.out.println("Invalid Input..");
						System.out.print("\n\nDo you want to change another order status? (y/n) : ");
						char config = scanner.next().charAt(0);
						reDirection(config, "orderStatus");
						break;
				}
				System.out.println("Order status successfully changed to " + statusMainArray[element]);
				System.out.print("\nTo access the main Menu, please enter 0 : ");
				int toMenuInput = scanner.nextInt();

				if (toMenuInput == 0) {
					homePage();
				} else {
					System.out.println("Invalid input..");
				}
			} 
			else if (confirmInput.equalsIgnoreCase("N")) {
				orderStatus();
			}

		} else if (status.equalsIgnoreCase("delivering")) {
			System.out.print("\nDo you want to change this order status? (y/n)");
			String confirmInput = scanner.nextLine();

			if (confirmInput.equalsIgnoreCase("Y")) {
				System.out.println("\n\t[1] Order Delivered\n\n");

				System.out.print("\nEnter Option : ");
				int processingOption = scanner.nextInt();

				switch (processingOption) {
					case 1:
						statusMainArray[element] = "Delivered";
						break;
					default:
						System.out.println("Invalid Input..");
						System.out.print("\n\nDo you want to change another order status? (y/n) : ");
						char config = scanner.next().charAt(0);
						reDirection(config, "orderStatus");
						break;
				}
				System.out.println("Order status successfully changed to " + statusMainArray[element]);
				System.out.print("\nTo access the main Menu, please enter 0 : ");
				int toMenuInput = scanner.nextInt();

				if (toMenuInput == 0) {
					homePage();
				} else {
					System.out.println("Invalid input..");
				}
			} else if (confirmInput.equalsIgnoreCase("N")) {
				orderStatus();
			}

		} else if (status.equalsIgnoreCase("delivered")) {
			System.out.println("\n\tCan't change this order status, Order already delivered..!\n");
			System.out.println("Invalid Input..");
			System.out.print("\n\nDo you want to change another order status? (y/n) : ");
			char config = scanner.next().charAt(0);
			reDirection(config, "orderStatus");
		}
	}

	public static void deleteOrder() {
		clearConsole();
		System.out.println("   _____       _      _          ____          _           ");
		System.out.println("  |  __ \\     | |    | |        / __ \\        | |          ");
		System.out.println("  | |  | | ___| | ___| |_ ___  | |  | |_ __ __| | ___ _ __ ");
		System.out.println("  | |  | |/ _ \\ |/ _ \\ __/ _ \\ | |  | | '__/ _` |/ _ \\ '__|");
		System.out.println("  | |__| |  __/ |  __/ ||  __/ | |__| | | | (_| |  __/ |   ");
		System.out.println("  |_____/ \\___|_|\\___|\\__\\___|  \\____/|_|  \\__,_|\\___|_|   ");
		System.out.println("\n------------------------------------------------------------\n\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Order ID: ");
		String searchInputOID = scanner.nextLine();
		boolean orderFound = false;
		int foundIndex = -1;

		for (int i = 0; i < orderIdMainArray.length; i++) {
			if (orderIdMainArray[i].equals(searchInputOID)) {
				foundIndex = i;
				orderFound = true;
				System.out.println("\nPhone Number : " + tpNumberMainArray[i]);
				System.out.println("Size         : " + sizeMainArray[i]);
				System.out.println("QTY          : " + qtyMainArray[i]);
				System.out.println("Amount       : " + amountMainArray[i]);
				System.out.println("Status       : " + statusMainArray[i]);
				break;
			}
		}
		if (!orderFound) {
			System.out.println("\nOrder ID not found in the system!");
			deleteOrder();
		} else {
			confirmDelete(foundIndex);
			System.out.print("\n\nDo you want to delete another order? (y/n) : ");
			char config = scanner.next().charAt(0);
			reDirection(config, "deleteOrder");
		}
	}

	public static String statusCheck(int statusNumber) {
		String tempStatus = "";
		switch (statusNumber) {
			case 0:
				tempStatus = "Processing";
				break;
			case 1:
				tempStatus = "Delivering";
				break;
			case 3:
				tempStatus = "Delivered";
				break;
		}
		return tempStatus;
	}

	public static String generateOrderID() {
		int tempOrderNumber = orderNumber;

		int[] tempNumOrder = new int[5];
		String idNum = "";
		String tag = "ODR#";
		String newOrderID = "";

		for (int i = 4; tempOrderNumber > 0; i--) {
			tempNumOrder[i] = tempOrderNumber % 10;
			tempOrderNumber /= 10;
		}
		for (int i = 0; i < tempNumOrder.length; i++) {
			idNum += tempNumOrder[i];
		}
		newOrderID = tag + idNum;
		return newOrderID;
	}

	public static void getPhoneNumber() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter Customer Phone Number: ");
		cusPhoneNumber = scanner.nextLine();

		if (cusPhoneNumber.length() != 10 || cusPhoneNumber.charAt(0) != '0') {
			System.out.println("\n\t Invalid Number.. Try again");
			System.out.print("\n\nDo you want to enter phone number again? (y/n) : ");
			char config = scanner.next().charAt(0);
			reDirection(config, "enterAgainPhoneNumber");
		}
	}

	public static void getSize() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter T-Shirt Size (XS/S/M/L/XL/XXL) : ");
		tShirtSize = scanner.nextLine();
		boolean validate = false;

		if(tShirtSize.equalsIgnoreCase("XS") ||
			tShirtSize.equalsIgnoreCase("S") ||
			tShirtSize.equalsIgnoreCase("M") ||
			tShirtSize.equalsIgnoreCase("L") ||
			tShirtSize.equalsIgnoreCase("XL") ||
			tShirtSize.equalsIgnoreCase("XXL")){
			validate = true;
		} else{
			System.out.print("\033[2A");
			System.out.print("\033[0J");
			getSize();
		}
	}

	public static void getQty() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter QTY : ");
		qty = scanner.nextInt();

		if (qty <= 0) {
			System.out.print("\033[2A");
			System.out.print("\033[0J");
			getQty();
		}
	}

	public static void confirmDelete(int index) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\n\nDo you want to delete this order? (y/n) : ");
		char configPlace = scanner.next().charAt(0);

		if (configPlace == 'Y' || configPlace == 'y') {
			System.out.print("\n\tOrder Deleted..!");
			shortenArrays(index);
		} else if (configPlace == 'N' || configPlace == 'n') {
			deleteOrder();
		} else {
			System.out.print("\033[3A");
			System.out.print("\033[0J");
			confirmDelete(index);
		}
	}

	public static void shortenArrays(int index) {
		String[] tempOID = new String[orderIdMainArray.length - 1];
		String[] tempSize = new String[sizeMainArray.length - 1];
		int[] tempQty = new int[qtyMainArray.length - 1];
		double[] tempAmount = new double[amountMainArray.length - 1];
		String[] tempTPNum = new String[tpNumberMainArray.length - 1];
		String[] tempStatus = new String[statusMainArray.length - 1];

		for (int j = 0; j < index; j++) {
			tempOID[j] = orderIdMainArray[j];
			tempSize[j] = sizeMainArray[j];
			tempQty[j] = qtyMainArray[j];
			tempAmount[j] = amountMainArray[j];
			tempTPNum[j] = tpNumberMainArray[j];
			tempStatus[j] = statusMainArray[j];
		}
		for (int j = index + 1; j < orderIdMainArray.length; j++) {
			tempOID[j - 1] = orderIdMainArray[j];
			tempSize[j - 1] = sizeMainArray[j];
			tempQty[j - 1] = qtyMainArray[j];
			tempAmount[j - 1] = amountMainArray[j];
			tempTPNum[j - 1] = tpNumberMainArray[j];
			tempStatus[j - 1] = statusMainArray[j];
		}
		orderIdMainArray = tempOID;
		sizeMainArray = tempSize;
		qtyMainArray = tempQty;
		amountMainArray = tempAmount;
		tpNumberMainArray = tempTPNum;
		statusMainArray = tempStatus;
	}

	public static void reDirection(char config, String configId) {
		if (config == 'Y' || config == 'y') {
			switch (configId) {
				case "placeOrderConfig":
					placeOrder();
					break;
				case "enterAgainPhoneNumber":
					System.out.print("\033[7A");
					System.out.print("\033[0J");
					getPhoneNumber();
					break;
				case "searchCustomer":
					searchCustomer();
					break;
				case "searchOrder":
					searchOrder();
					break;
				case "deleteOrder":
					deleteOrder();
					break;
				case "orderStatus":
					orderStatus();
					break;
				default:
					System.out.println("Redirecting to home page.");
					homePage();
					break;
			}
		} else if (config == 'N' || config == 'n') {
			homePage();
		} else {
			homePage();
		}
	}

	public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c",
						"cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}