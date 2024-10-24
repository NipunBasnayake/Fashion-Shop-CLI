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

class Customer {
    private String tpNumber;
    private int totalQuantity;
    private double totalAmount;
    private int[] sizeQuantities;

    public Customer(String tpNumber) {
        this.tpNumber = tpNumber;
        this.totalQuantity = 0;
        this.totalAmount = 0.0;
        this.sizeQuantities = new int[6];
    }

	public Customer(String tpNumber, int qty, double amount) {
        this.tpNumber = tpNumber;
        this.totalQuantity = qty;
        this.totalAmount = amount;
        this.sizeQuantities = new int[6];
        initializeSizeQuantities();
    }

    public String getTpNumber() {
        return tpNumber;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void addQuantity(int quantity) {
        this.totalQuantity += quantity;
    }

    public void addAmount(double amount) {
        this.totalAmount += amount;
    }

    public void initializeSizeQuantities() {
        for (int i = 0; i < sizeQuantities.length; i++) {
            sizeQuantities[i] = 0;
        }
    }

    public void updateSizeQuantity(String size, int quantity) {
        switch (size.toUpperCase()) {
            case "XS":
                sizeQuantities[0] += quantity;
                break;
            case "S":
                sizeQuantities[1] += quantity;
                break;
            case "M":
                sizeQuantities[2] += quantity;
                break;
            case "L":
                sizeQuantities[3] += quantity;
                break;
            case "XL":
                sizeQuantities[4] += quantity;
                break;
            case "XXL":
                sizeQuantities[5] += quantity;
                break;
            default:
                System.out.println("Unknown size: " + size);
                break;
        }
    }

    public int[] getSizeQuantities() {
        return sizeQuantities;
    }
}

class Items {
    private String size;
    private int totalQuantity;
    private double totalAmount;

    public Items(String size, int totalQuantity, double totalAmount) {
        this.size = size;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
    }

    public String getSize() {
        return size;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void addQuantity(int quantity) {
        this.totalQuantity += quantity;
    }

    public void addAmount(double amount) {
        this.totalAmount += amount;
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

		for (int i = 0; i < ordersMainArray.length; i++) {
			if (ordersMainArray[i].getOrderId().equals(searchInputOID)) {
				found = true;
				System.out.println("\nPhone Number : " + ordersMainArray[i].getTpNumber());
				System.out.println("Size         : " + ordersMainArray[i].getSize());
				System.out.println("QTY          : " + ordersMainArray[i].getQuantity());
				System.out.println("Amount       : " + ordersMainArray[i].getAmount());
				System.out.println("Status       : " + ordersMainArray[i].getStatus());
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

		Customer[] customers = new Customer[ordersMainArray.length];
		int uniqueCustomers = 0;
		
		for (Orders order : ordersMainArray) {
			String tpNumber = order.getTpNumber();
			int qty = order.getQuantity();
			double amount = order.getAmount();
		
			boolean isNewCustomer = true;
			int customerIndex = -1;
		
			for (int i = 0; i < uniqueCustomers; i++) {
				if (customers[i].getTpNumber().equals(tpNumber)) {
					isNewCustomer = false;
					customerIndex = i;
					break;
				}
			}
		
			if (isNewCustomer) {
				customers[uniqueCustomers] = new Customer(tpNumber, qty, amount);
				uniqueCustomers++;
			} else {
				customers[customerIndex].addQuantity(qty);
				customers[customerIndex].addAmount(amount);
			}
		}
		
		for (int i = 0; i < uniqueCustomers - 1; i++) {
			for (int j = 0; j < uniqueCustomers - i - 1; j++) {
				if (customers[j].getTotalAmount() < customers[j + 1].getTotalAmount()) {
					Customer temp = customers[j];
					customers[j] = customers[j + 1];
					customers[j + 1] = temp;
				}
			}
		}
		
		System.out.printf("\t+---------------+---------------+---------------+\n");
		System.out.printf("\t| %-13s | %-13s | %-13s |\n", "Customer ID", "All QTY", "Total Amount");
		System.out.printf("\t+---------------+---------------+---------------+\n");
		
		for (int i = 0; i < uniqueCustomers; i++) {
			System.out.printf("\t|               |               |               |\n");
			System.out.printf("\t| %-13s | %-13d | %-13.2f |\n",
					customers[i].getTpNumber(),
					customers[i].getTotalQuantity(),
					customers[i].getTotalAmount());
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

		Customer[] customers = new Customer[ordersMainArray.length];
		int uniqueCustomers = 0;

		for (Orders order : ordersMainArray) {
            String tpNumber = order.getTpNumber();
            int qty = order.getQuantity();
            double amount = order.getAmount();

            boolean isNewCustomer = true;
            int customerIndex = -1;
			
            for (int i = 0; i < uniqueCustomers; i++) {
                if (customers[i].getTpNumber().equals(tpNumber)) {
                    isNewCustomer = false;
                    customerIndex = i;
                    break;
                }
            }

            if (isNewCustomer) {
                customers[uniqueCustomers] = new Customer(tpNumber, qty, amount);
                uniqueCustomers++;
            } else {
                customers[customerIndex].addQuantity(qty);
                customers[customerIndex].addAmount(amount);
            }
        }

		System.out.printf("\t+---------------+---------------+---------------+\n");
		System.out.printf("\t| %-13s | %-13s | %-13s |\n", "Customer ID", "All QTY", "Total Amount");
		System.out.printf("\t+---------------+---------------+---------------+\n");

		for (int i = 0; i < uniqueCustomers; i++) {
			System.out.printf("\t|               |               |               |\n");
			System.out.printf("\t| %-13s | %-13d | %-13.2f |\n", customers[i].getTpNumber(), customers[i].getTotalQuantity(), customers[i].getTotalAmount());
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

		Customer[] customers = new Customer[ordersMainArray.length];
    int uniqueCustomers = 0;

    for (Orders order : ordersMainArray) {
        String tpNumber = order.getTpNumber();
        int qty = order.getQuantity();
        double amount = order.getAmount();
        String size = order.getSize();

        boolean isNewCustomer = true;
        int customerIndex = -1;

        for (int i = 0; i < uniqueCustomers; i++) {
            if (customers[i].getTpNumber().equals(tpNumber)) {
                isNewCustomer = false;
                customerIndex = i;
                break;
            }
        }

        if (isNewCustomer) {
            customers[uniqueCustomers] = new Customer(tpNumber);
            customers[uniqueCustomers].initializeSizeQuantities();
            customerIndex = uniqueCustomers;
            uniqueCustomers++;
        }
        customers[customerIndex].updateSizeQuantity(size, qty);
        customers[customerIndex].addAmount(amount);
    }

    System.out.printf("\t+---------------+----+----+----+----+----+-----+---------------+\n");
    System.out.printf("\t| Phone Number  | XS | S  | M  | L  | XL | XXL |  Total Amount |\n");
    System.out.printf("\t+---------------+----+----+----+----+----+-----+---------------+\n");

    for (int i = 0; i < uniqueCustomers; i++) {
        int[] sizeQuantities = customers[i].getSizeQuantities();
        System.out.printf("\t|               |    |    |    |    |    |     |               |\n");
        System.out.printf("\t| %-13s | %-2d | %-2d | %-2d | %-2d | %-2d | %-3d |   %-12.2f|\n",
                customers[i].getTpNumber(),
                sizeQuantities[0], sizeQuantities[1], sizeQuantities[2], sizeQuantities[3], sizeQuantities[4], sizeQuantities[5],
                customers[i].getTotalAmount());
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

		Items[] items = new Items[ordersMainArray.length];
		int uniqueSizesCount = 0;
		
		for (Orders order : ordersMainArray) {
			String size = order.getSize();
			int qty = order.getQuantity();
			double amount = order.getAmount();
		
			boolean isNewSize = true;
			int sizeIndex = -1;
		
			for (int i = 0; i < uniqueSizesCount; i++) {
				if (items[i].getSize().equals(size)) {
					isNewSize = false;
					sizeIndex = i;
					break;
				}
			}
		
			if (isNewSize) {
				items[uniqueSizesCount] = new Items(size, qty, amount);
				uniqueSizesCount++;
			} else {
				items[sizeIndex].addQuantity(qty);
				items[sizeIndex].addAmount(amount);
			}
		}

		for (int i = 0; i < uniqueSizesCount - 1; i++) {
			for (int j = 0; j < uniqueSizesCount - i - 1; j++) {
				if (items[j].getTotalQuantity() < items[j + 1].getTotalQuantity()) {
					Items temp = items[j];
					items[j] = items[j + 1];
					items[j + 1] = temp;
				}
			}
		}

		System.out.printf("\n\t+---------------+---------------+---------------+\n");
		System.out.printf("\t| %-13s | %-13s | %-13s |\n", "Size", "Total QTY", "Total Amount");
		System.out.printf("\t+---------------+---------------+---------------+\n");
		for (int i = 0; i < uniqueSizesCount; i++) {
			System.out.printf("\t|               |               |               |\n");
 			System.out.printf("\t| %-13s | %-13d | %-13.2f |\n", items[i].getSize(), items[i].getTotalQuantity(), items[i].getTotalAmount());
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

		Items[] items = new Items[ordersMainArray.length];
		int uniqueSizesCount = 0;

		for (Orders order : ordersMainArray) {
			String size = order.getSize();
			int qty = order.getQuantity();
			double amount = order.getAmount();
		
			boolean isNewSize = true;
			int sizeIndex = -1;
		
			for (int i = 0; i < uniqueSizesCount; i++) {
				if (items[i].getSize().equals(size)) {
					isNewSize = false;
					sizeIndex = i;
					break;
				}
			}
		
			if (isNewSize) {
				items[uniqueSizesCount] = new Items(size, qty, amount);
				uniqueSizesCount++;
			} else {
				items[sizeIndex].addQuantity(qty);
				items[sizeIndex].addAmount(amount);
			}
		}

		for (int i = 0; i < uniqueSizesCount - 1; i++) {
			for (int j = 0; j < uniqueSizesCount - i - 1; j++) {
				if (items[j].getTotalAmount() < items[j + 1].getTotalAmount()) {
					Items temp = items[j];
					items[j] = items[j + 1];
					items[j + 1] = temp;
				}
			}
		}

		System.out.printf("\n\t+---------------+---------------+---------------+\n");
		System.out.printf("\t| %-13s | %-13s | %-13s |\n", "Size", "Total QTY", "Total Amount");
		System.out.printf("\t+---------------+---------------+---------------+\n");
		for (int i = 0; i < uniqueSizesCount; i++) {
			System.out.printf("\t|               |               |               |\n");
			System.out.printf("\t| %-13s | %-13d | %-13.2f |\n", items[i].getSize(), items[i].getTotalQuantity(), items[i].getTotalAmount());
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
		for (Orders order : ordersMainArray) {
			System.out.printf("\t| %-13s | %-13s | %-13s | %-13d | %-13.2f | %-13s |\n",
				order.getOrderId(),
				order.getTpNumber(),
				order.getSize(),
				order.getQuantity(),
				order.getAmount(),
				order.getStatus());
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

    Orders[] sortedOrders = new Orders[ordersMainArray.length];
    for (int i = 0; i < ordersMainArray.length; i++) {
        sortedOrders[i] = ordersMainArray[i];
    }
    
    for (int i = 0; i < sortedOrders.length - 1; i++) {
        for (int j = 0; j < sortedOrders.length - 1 - i; j++) {
            if (sortedOrders[j].getAmount() < sortedOrders[j + 1].getAmount()) {
                Orders temp = sortedOrders[j];
                sortedOrders[j] = sortedOrders[j + 1];
                sortedOrders[j + 1] = temp;
            }
        }
    }

		System.out.printf("\n\t+---------------+---------------+---------------+---------------+---------------+---------------+\n");
		System.out.printf("\t| %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |\n", "Order ID", "Customer ID", "Size", "Quantity", "Amount", "Status");
		System.out.printf("\t+---------------+---------------+---------------+---------------+---------------+---------------+\n");
		for (Orders order : sortedOrders) {
			System.out.printf("\t| %-13s | %-13s | %-13s | %-13d | %-13.2f | %-13s |\n",
				order.getOrderId(),
				order.getTpNumber(),
				order.getSize(),
				order.getQuantity(),
				order.getAmount(),
				order.getStatus());
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
		System.out.println("   \\____/|_|  \\___|\\___|_|    |_____/ \\__\\__,_|\\__|\\__,_|___/");
		System.out.println("\n--------------------------------------------------------------\n\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Order ID: ");
		String sOrderStatusOID = scanner.nextLine();
		String status = "";
		int element = -1;

		for (int i = 0; i < ordersMainArray.length; i++) {
			if (ordersMainArray[i].getOrderId().equals(sOrderStatusOID)) {
				System.out.println("\nPhone Number : " + ordersMainArray[i].getTpNumber());
				System.out.println("Size         : " + ordersMainArray[i].getSize());
				System.out.println("QTY          : " + ordersMainArray[i].getQuantity());
				System.out.println("Amount       : " + ordersMainArray[i].getAmount());
				element = i;
				status = ordersMainArray[i].getStatus();
				System.out.println("Status       : " + status);
				break;
			}
		}

		if (element == -1) {
			System.out.println("\nOrder ID not found.");
			System.out.print("\nDo you want to search another Order ID? (y/n) : ");
			char config = scanner.next().charAt(0);
			reDirection(config, "orderStatus");
			return;
		}

		if (status.equalsIgnoreCase("processing")) {
			System.out.print("\nDo you want to change this order status? (y/n) : ");
			String confirmInput = scanner.nextLine();

			if (confirmInput.equalsIgnoreCase("Y")) {
				System.out.println("\n\t[1] Order Delivering");
				System.out.println("\t[2] Order Delivered");

				System.out.print("\nEnter Option : ");
				int processingOption = scanner.nextInt();
				scanner.nextLine();

				switch (processingOption) {
					case 1:
						ordersMainArray[element].setStatus("Delivering");
						break;
					case 2:
						ordersMainArray[element].setStatus("Delivered");
						break;
					default:
						System.out.println("Invalid Input..");
						System.out.print("\nDo you want to change another order status? (y/n) : ");
						char config = scanner.next().charAt(0);
						reDirection(config, "orderStatus");
						return;
				}
				System.out.println("Order status successfully changed to " + ordersMainArray[element].getStatus());
			}
		} else if (status.equalsIgnoreCase("delivering")) {
			System.out.print("\nDo you want to change this order status? (y/n) : ");
			String confirmInput = scanner.nextLine();

			if (confirmInput.equalsIgnoreCase("Y")) {
				System.out.println("\n\t[1] Order Delivered");

				System.out.print("\nEnter Option : ");
				int processingOption = scanner.nextInt();
				scanner.nextLine();

				if (processingOption == 1) {
					ordersMainArray[element].setStatus("Delivered");
				} else {
					System.out.println("Invalid Input..");
					System.out.print("\nDo you want to change another order status? (y/n) : ");
					char config = scanner.next().charAt(0);
					reDirection(config, "orderStatus");
					return;
				}
				System.out.println("Order status successfully changed to " + ordersMainArray[element].getStatus());
			}
		} else if (status.equalsIgnoreCase("delivered")) {
			System.out.println("\n\tCan't change this order status, Order already delivered!");
		}

		System.out.print("\nTo access the main Menu, please enter 0 : ");
		while (!scanner.hasNextInt()) {
			System.out.println("Invalid input, please enter 0 to go back to the main Menu.");
			scanner.next();
		}
		int toMenuInput = scanner.nextInt();

		if (toMenuInput == 0) {
			homePage();
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
		String sOrderStatusOID = scanner.nextLine();
		boolean orderFound = false;
		int foundIndex = -1;

		for (int i = 0; i < ordersMainArray.length; i++) {
			if (ordersMainArray[i].getOrderId().equals(sOrderStatusOID)) {
				System.out.println("\nPhone Number : " + ordersMainArray[i].getTpNumber());
				System.out.println("Size         : " + ordersMainArray[i].getSize());
				System.out.println("QTY          : " + ordersMainArray[i].getQuantity());
				System.out.println("Amount       : " + ordersMainArray[i].getAmount());
				System.out.println("Status       : " + ordersMainArray[i].getStatus());
				foundIndex = i;
				orderFound = true;
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
		Orders[] tempOrders = new Orders[ordersMainArray.length + 1];

		for (int j = 0; j < index; j++) {
			tempOrders[j].setOrderId(ordersMainArray[j].getOrderId());
			tempOrders[j].setSize(ordersMainArray[j].getSize());
			tempOrders[j].setQuantity(ordersMainArray[j].getQuantity());
			tempOrders[j].setAmount(ordersMainArray[j].getAmount());
			tempOrders[j].setTpNumber(ordersMainArray[j].getTpNumber());
			tempOrders[j].setStatus(ordersMainArray[j].getStatus());
		}
		for (int j = index + 1; j < ordersMainArray.length; j++) {
			tempOrders[j-1].setOrderId(ordersMainArray[j].getOrderId());
			tempOrders[j-1].setSize(ordersMainArray[j].getSize());
			tempOrders[j-1].setQuantity(ordersMainArray[j].getQuantity());
			tempOrders[j-1].setAmount(ordersMainArray[j].getAmount());
			tempOrders[j-1].setTpNumber(ordersMainArray[j].getTpNumber());
			tempOrders[j-1].setStatus(ordersMainArray[j].getStatus());
		}
		ordersMainArray = tempOrders;
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