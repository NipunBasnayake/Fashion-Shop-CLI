import java.util.*;

class FashionShop {
	static int orderNumber = 0;
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

	public static void main(String args[]) {
		homePage();
	}

	public static void homePage() {
		clearConsole();
		System.out.println(
				"\n   /$$$$$$$$                 /$$       /$$                            /$$$$$$  /$$                          ");
		System.out.println(
				"  | $$_____/                | $$      |__/                           /$$__  $$| $$                          ");
		System.out.println(
				"  | $$    /$$$$$$   /$$$$$$$| $$$$$$$  /$$  /$$$$$$  /$$$$$$$       | $$  \\__/| $$$$$$$   /$$$$$$   /$$$$$$");
		System.out.println(
				"  | $$$$$|____  $$ /$$_____/| $$__  $$| $$ /$$__  $$| $$__  $$      |  $$$$$$ | $$__  $$ /$$__  $$ /$$__  $$");
		System.out.println(
				"  | $$__/ /$$$$$$$|  $$$$$$ | $$  \\ $$| $$| $$  \\ $$| $$  \\ $$       \\____  $$| $$  \\ $$| $$  \\ $$| $$  \\ $$");
		System.out.println(
				"  | $$   /$$__  $$ \\____  $$| $$  | $$| $$| $$  | $$| $$  | $$       /$$  \\ $$| $$  | $$| $$  | $$| $$  | $$");
		System.out.println(
				"  | $$  |  $$$$$$$ /$$$$$$$/| $$  | $$| $$|  $$$$$$/| $$  | $$      |  $$$$$$/| $$  | $$|  $$$$$$/| $$$$$$$/");
		System.out.println(
				"  |__/   \\_______/|_______/ |__/  |__/|__/ \\______/ |__/  |__/       \\______/ |__/  |__/ \\______/ | $$____/ ");
		System.out.println(
				"                                                                                                  | $$      ");
		System.out.println(
				"                                                                                                  | $$      ");
		System.out.println(
				"                                                                                                  |__/      ");
		System.out.println(
				"\n------------------------------------------------------------------------------------------------------------\n\n");

		System.out.println("\t\t[1] Place Order\t\t\t\t\t[2] Search Customer\n\n");
		System.out.println("\t\t[3] Search Order\t\t\t\t[4] View Reports\n\n");
		System.out.println("\t\t[5] Set Order Status\t\t\t\t[6] Delete Order\n\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("\n\t\tInput Option : ");
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
		orderNumber += 1;

		System.out.println("   _____  _                   ____          _           ");
		System.out.println("  |  __ \\| |                 / __ \\        | |          ");
		System.out.println("  | |__) | | __ _  ___ ___  | |  | |_ __ __| | ___ _ __ ");
		System.out.println("  |  ___/| |/ _` |/ __/ _ \\ | |  | | '__/ _` |/ _ \\ '__|");
		System.out.println("  | |    | | (_| | (_|  __/ | |__| | | | (_| |  __/ |   ");
		System.out.println("  |_|    |_|\\__,_|\\___\\___|  \\____/|_|  \\__,_|\\___|_|   ");
		System.out.println("\n---------------------------------------------------------\n\n");

		Scanner scanner = new Scanner(System.in);

		String orderID = generateOrderID(orderNumber);
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

		System.out.print("\n\nDo you want to place another order? (Y/N) : ");
		char config = scanner.next().charAt(0);
		reDirection(config, "placeOrderConfig");
	}

	public static void searchCustomer() {
		clearConsole();
		System.out.println("\n    _____                     _        _____          _                           ");
		System.out.println("   / ____|                   | |      / ____|        | |                          ");
		System.out.println("  | (___   ___  __ _ _ __ ___| |__   | |    _   _ ___| |_ ___  _ __ ___   ___ _ __");
		System.out
				.println("   \\___ \\ / _ \\/ _` | '__/ __| '_ \\  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__|");
		System.out.println("   ____) |  __/ (_| | | | (__| | | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  ");
		System.out
				.println("  |_____/ \\___|\\__,_|_|  \\___|_| |_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  ");
		System.out
				.println("\n-------------------------------------------------------------------------------------\n\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Customer Phone Number: ");
		String searchInputTP = scanner.nextLine();

		boolean found = false;
		double totalAmount = 0;

		String[] processedSizes = new String[tpNumberMainArray.length];
		int[] totalQtyForSize = new int[tpNumberMainArray.length];
		double[] totalAmountForSize = new double[tpNumberMainArray.length];
		int processedCount = 0;

		for (int i = 0; i < tpNumberMainArray.length; i++) {
			boolean isMatch = true;
			for (int j = 0; j < searchInputTP.length(); j++) {
				if (searchInputTP.charAt(j) != tpNumberMainArray[i].charAt(j)) {
					isMatch = false;
					break;
				}
			}

			if (isMatch) {
				if (!found) {
					System.out.println("\n\t+---------------+---------------+---------------+");
					System.out.println("\t|\tSize\t|\tQTY\t|\tAmount\t|");
					System.out.println("\t+---------------+---------------+---------------+");
					found = true;
				}

				boolean sizeAlreadyProcessed = false;
				int index = -1;
				for (int k = 0; k < processedCount; k++) {
					if (processedSizes[k].equals(sizeMainArray[i])) {
						sizeAlreadyProcessed = true;
						index = k;
						break;
					}
				}

				if (sizeAlreadyProcessed) {
					totalQtyForSize[index] += qtyMainArray[i];
					totalAmountForSize[index] += amountMainArray[i];
				} else {
					processedSizes[processedCount] = sizeMainArray[i];
					totalQtyForSize[processedCount] = qtyMainArray[i];
					totalAmountForSize[processedCount] = amountMainArray[i];
					processedCount++;
				}

				totalAmount += amountMainArray[i];
			}
		}

		for (int i = 0; i < processedCount; i++) {
			System.out.println("\t|\t" + processedSizes[i] + "\t|\t" + totalQtyForSize[i] + "\t|\t"
					+ totalAmountForSize[i] + "\t|");
			System.out.println("\t|               |               |               |");
		}

		if (found) {
			System.out.println("\t+---------------+---------------+---------------+");
			System.out.println("\tTotal Amount: " + totalAmount);
		} else {
			System.out.println("No records found for this phone number.");
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
			System.out.println("\n\tInvalid ID..");
			System.out.print("\n\nDo you want to search another customer report? (Y/N): ");
			char config = scanner.next().charAt(0);
			reDirection(config, "searchOrder");
		}
		System.out.print("\n\nDo you want to search another customer report? (Y/N): ");
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
		System.out.print("\nInput Option : ");
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
				System.out.println("Invalid input..");
				break;
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
			System.out.print("\n\nDo you want to delete another order? (Y/N) : ");
			char config = scanner.next().charAt(0);
			reDirection(config, "deleteOrder");
		}
	}

	public static void customerReports() {
		clearConsole();
		System.out.println("    _____          _                              _____                       _       ");
		System.out.println("   / ____|        | |                            |  __ \\                     | |      ");
		System.out.println("  | |    _   _ ___| |_ ___  _ __ ___   ___ _ __  | |__) |___ _ __   ___  _ __| |_ ___ ");
		System.out.println(
				"  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__| |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|");
		System.out.println(
				"  | |___| |_| \\__ \\ || (_) | | | | | |  __/ |    | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\");
		System.out.println(
				"   \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|    |_|  \\_\\___| .__/ \\___/|_|   \\__|___/");
		System.out.println("                                                            | |                       ");
		System.out.println("                                                            |_|                       ");
		System.out.println(
				"----------------------------------------------------------------------------------------\n\n");

		System.out.println("\t[1] Best in Customers\n");
		System.out.println("\t[2] View Customers\n");
		System.out.println("\t[3] All Customer Reports\n");

		Scanner scanner = new Scanner(System.in);
		System.out.print("\nInput Option : ");
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
				System.out.println("Invalid input..");
				break;
		}
	}

	public static void bestInCustomers() {
		clearConsole();

		System.out.println("   ____            _     _____          _____          _                                ");
		System.out.println("  |  _ \\          | |   |_   _|        / ____|        | |                               ");
		System.out.println("  | |_) | ___  ___| |_    | |  _ __   | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___ ");
		System.out.println(
				"  |  _ < / _ \\/ __| __|   | | | '_ \\  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__/ __|");
		System.out.println(
				"  | |_) |  __/\\__ \\ |_   _| |_| | | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\");
		System.out.println(
				"  |____/ \\___||___/\\__| |_____|_| |_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/");
		System.out.println(
				"----------------------------------------------------------------------------------------\n\n");

		String[] customerIDs = new String[tpNumberMainArray.length];
		int[] totalQty = new int[tpNumberMainArray.length];
		double[] totalAmount = new double[tpNumberMainArray.length];
		int uniqueCustomers = 0;

		for (int i = 0; i < tpNumberMainArray.length; i++) {
			boolean isNewCustomer = true;
			int customerIndex = -1;

			for (int j = 0; j < uniqueCustomers; j++) {
				if (tpNumberMainArray[i].equals(customerIDs[j])) {
					isNewCustomer = false;
					customerIndex = j;
					break;
				}
			}

			if (isNewCustomer) {
				customerIDs[uniqueCustomers] = tpNumberMainArray[i];
				totalQty[uniqueCustomers] = qtyMainArray[i];
				totalAmount[uniqueCustomers] = amountMainArray[i];
				uniqueCustomers++;
			} else {
				totalQty[customerIndex] += qtyMainArray[i];
				totalAmount[customerIndex] += amountMainArray[i];
			}
		}

		for (int i = 0; i < uniqueCustomers - 1; i++) {
			for (int j = 0; j < uniqueCustomers - i - 1; j++) {
				if (totalAmount[j] < totalAmount[j + 1]) {

					String tempID = customerIDs[j];
					customerIDs[j] = customerIDs[j + 1];
					customerIDs[j + 1] = tempID;

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
		System.out.printf("\t| %-13s | %-13s | %-13s |\n", "Customer ID", "All QTY", "Total Amount");
		System.out.printf("\t+---------------+---------------+---------------+\n");

		for (int i = 0; i < uniqueCustomers; i++) {
			System.out.printf("\t| %-13s | %-13d | %-13.2f |\n", customerIDs[i], totalQty[i], totalAmount[i]);
			System.out.printf("\t+---------------+---------------+---------------+\n");
		}
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nTo access the main Menu, please enter 0 : ");
		int toMenuInput = scanner.nextInt();

		if (toMenuInput == 0) {
			homePage();
		} else {
			System.out.println("Invalid input..");
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
		System.out.println("-----------------------------------------------------------------------------------\n\n");

		String[] customerIDs = new String[tpNumberMainArray.length];
		int[] totalQty = new int[tpNumberMainArray.length];
		double[] totalAmount = new double[tpNumberMainArray.length];
		int uniqueCustomers = 0;

		for (int i = 0; i < tpNumberMainArray.length; i++) {
			boolean isNewCustomer = true;
			int customerIndex = -1;

			for (int j = 0; j < uniqueCustomers; j++) {
				if (tpNumberMainArray[i].equals(customerIDs[j])) {
					isNewCustomer = false;
					customerIndex = j;
					break;
				}
			}

			if (isNewCustomer) {
				customerIDs[uniqueCustomers] = tpNumberMainArray[i];
				totalQty[uniqueCustomers] = qtyMainArray[i];
				totalAmount[uniqueCustomers] = amountMainArray[i];
				uniqueCustomers++;
			} else {
				totalQty[customerIndex] += qtyMainArray[i];
				totalAmount[customerIndex] += amountMainArray[i];
			}
		}

		System.out.printf("\n\t+---------------+---------------+---------------+\n");
		System.out.printf("\t| %-13s | %-13s | %-13s |\n", "Customer ID", "All QTY", "Total Amount");
		System.out.printf("\t+---------------+---------------+---------------+\n");

		for (int i = 0; i < uniqueCustomers; i++) {
			System.out.printf("\t| %-13s | %-13d | %-13.2f |\n", customerIDs[i], totalQty[i], totalAmount[i]);
			System.out.printf("\t+---------------+---------------+---------------+\n");
		}
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nTo access the main Menu, please enter 0 : ");
		int toMenuInput = scanner.nextInt();

		if (toMenuInput == 0) {
			homePage();
		} else {
			System.out.println("Invalid input..");
		}
	}

	public static void allCustomerReports() {
		clearConsole();
		System.out.println(
				"            _ _    _____          _                                  _____                       _       ");
		System.out.println(
				"      /\\   | | |  / ____|        | |                                |  __ \\                     | |      ");
		System.out.println(
				"     /  \\  | | | | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___  | |__) |___ _ __   ___  _ __| |_ ___ ");
		System.out.println(
				"    / /\\ \\ | | | | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__/ __| |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|");
		System.out.println(
				"   / ____ \\| | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\ | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\");
		System.out.println(
				"  /_/    \\_\\_|_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/ |_|  \\_\\___| .__/ \\___/|_|   \\__|___/");
		System.out.println(
				"                                                                                | |                      ");
		System.out.println(
				"                                                                                |_|                      ");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------\n\n");

		String[] customerIDs = new String[tpNumberMainArray.length];
		int[] qtyXS = new int[tpNumberMainArray.length];
		int[] qtyS = new int[tpNumberMainArray.length];
		int[] qtyM = new int[tpNumberMainArray.length];
		int[] qtyL = new int[tpNumberMainArray.length];
		int[] qtyXL = new int[tpNumberMainArray.length];
		int[] qtyXXL = new int[tpNumberMainArray.length];
		double[] totalAmount = new double[tpNumberMainArray.length];
		int uniqueCustomers = 0;

		for (int i = 0; i < tpNumberMainArray.length; i++) {
			boolean isNewCustomer = true;
			int customerIndex = -1;

			for (int j = 0; j < uniqueCustomers; j++) {
				if (tpNumberMainArray[i].equals(customerIDs[j])) {
					isNewCustomer = false;
					customerIndex = j;
					break;
				}
			}

			if (isNewCustomer) {
				customerIDs[uniqueCustomers] = tpNumberMainArray[i];
				customerIndex = uniqueCustomers;
				uniqueCustomers++;
			}

			switch (sizeMainArray[i]) {
				case "XS":
					qtyXS[customerIndex] += qtyMainArray[i];
					break;
				case "S":
					qtyS[customerIndex] += qtyMainArray[i];
					break;
				case "M":
					qtyM[customerIndex] += qtyMainArray[i];
					break;
				case "L":
					qtyL[customerIndex] += qtyMainArray[i];
					break;
				case "XL":
					qtyXL[customerIndex] += qtyMainArray[i];
					break;
				case "XXL":
					qtyXXL[customerIndex] += qtyMainArray[i];
					break;
			}
			totalAmount[customerIndex] += amountMainArray[i];
		}

		System.out.printf("\n\t+---------------+----+----+----+----+----+-----+--------------+\n");
		System.out.printf("\t| Phone Number  | XS | S  | M  | L  | XL | XXL|  Total Amount |\n");
		System.out.printf("\t+---------------+----+----+----+----+----+-----+--------------+\n");

		for (int i = 0; i < uniqueCustomers; i++) {
			System.out.printf("\t| %-13s | %-2d | %-2d | %-2d | %-2d | %-2d | %-3d | %-12.2f |\n",
					customerIDs[i], qtyXS[i], qtyS[i], qtyM[i], qtyL[i], qtyXL[i], qtyXXL[i], totalAmount[i]);
			System.out.printf("\t+---------------+----+----+----+----+----+-----+--------------+\n");
		}

		Scanner scanner = new Scanner(System.in);
		System.out.print("\nTo access the main Menu, please enter 0 : ");
		int toMenuInput = scanner.nextInt();

		if (toMenuInput == 0) {
			homePage();
		} else {
			System.out.println("Invalid input..");
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
	}

	public static void confirmOrder() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\n\nDo you want to place this order? (Y/N) : ");
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
		String[] tempOID = new String[orderIdMainArray.length + 1];
		String[] tempSize = new String[sizeMainArray.length + 1];
		int[] tempQty = new int[qtyMainArray.length + 1];
		double[] tempAmount = new double[amountMainArray.length + 1];
		String[] tempTPNum = new String[tpNumberMainArray.length + 1];
		String[] tempStatus = new String[statusMainArray.length + 1];

		for (int j = 0; j < orderIdMainArray.length; j++) {
			tempOID[j] = orderIdMainArray[j];
		}
		for (int j = 0; j < sizeMainArray.length; j++) {
			tempSize[j] = sizeMainArray[j];
		}
		for (int j = 0; j < qtyMainArray.length; j++) {
			tempQty[j] = qtyMainArray[j];
		}
		for (int j = 0; j < amountMainArray.length; j++) {
			tempAmount[j] = amountMainArray[j];
		}
		for (int j = 0; j < tpNumberMainArray.length; j++) {
			tempTPNum[j] = tpNumberMainArray[j];
		}
		for (int j = 0; j < statusMainArray.length; j++) {
			tempStatus[j] = statusMainArray[j];
		}
		tempOID[tempOID.length - 1] = generateOrderID(orderNumber);
		tempSize[tempSize.length - 1] = tShirtSize;
		tempQty[tempQty.length - 1] = qty;
		tempAmount[tempAmount.length - 1] = amount;
		tempTPNum[tempTPNum.length - 1] = cusPhoneNumber;
		tempStatus[tempStatus.length - 1] = statusCheck(PROCESSING);

		orderIdMainArray = tempOID;
		sizeMainArray = tempSize;
		qtyMainArray = tempQty;
		amountMainArray = tempAmount;
		tpNumberMainArray = tempTPNum;
		statusMainArray = tempStatus;

		// System.out.println(Arrays.toString(orderIdMainArray));
		// System.out.println(Arrays.toString(sizeMainArray));
		// System.out.println(Arrays.toString(qtyMainArray));
		// System.out.println(Arrays.toString(amountMainArray));
		// System.out.println(Arrays.toString(tpNumberMainArray));
	}

	public static String statusCheck(int statucNumber) {
		String tempStatus = "";
		switch (statucNumber) {
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

	public static String generateOrderID(int orderNumber) {
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
			System.out.println("\t Invalid Number.. Try again");
			System.out.print("\n\nDo you want to enter phone number again? (Y/N) : ");
			char config = scanner.next().charAt(0);
			reDirection(config, "placeOrderConfig");
		}
	}

	public static void getSize() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter T-Shirt Size (XS/S/M/L/XL/XXL) : ");
		tShirtSize = scanner.nextLine();
		boolean validate = false;

		switch (tShirtSize) {
			case "XS":
				validate = true;
				break;
			case "S":
				validate = true;
				break;
			case "M":
				validate = true;
				break;
			case "L":
				validate = true;
				break;
			case "XL":
				validate = true;
				break;
			case "XXL":
				validate = true;
				break;
			default:
				validate = false;
		}

		if (!validate) {
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
		System.out.print("\n\nDo you want to delete this order? (Y/N) : ");
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
				default:
					System.out.println("Redirecting to home page.");
					homePage();
					break;
			}
		} else if (config == 'N' || config == 'n') {
			homePage();
		} else {
			System.out.println("\n\nInvalid input!");
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
