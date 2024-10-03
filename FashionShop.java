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
			case 6:
				deleteOrder();
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
			System.out.print("\n\nDo you want to place another order? (Y/N) : ");
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
		System.out.print("Enter Customer Phone Number : ");
		String searchInputTP = scanner.nextLine();

		boolean found = false;
		double totalAmount = 0;
		boolean[] processed = new boolean[tpNumberMainArray.length];

		for (int i = 0; i < tpNumberMainArray.length; i++) {
			if (tpNumberMainArray[i].equals(searchInputTP) && !processed[i]) {
				if (!found) {
					System.out.println("\n\t+---------------+---------------+---------------+");
					System.out.println("\t|\tSize\t|\tQTY\t|\tAmount\t|");
					System.out.println("\t+---------------+---------------+---------------+");
					System.out.println("\t|               |               |               |");
					found = true;
				}

				int totalQty = qtyMainArray[i];
				double amountForSize = amountMainArray[i];
				processed[i] = true;

				for (int j = i + 1; j < tpNumberMainArray.length; j++) {
					if (tpNumberMainArray[j].equals(searchInputTP) && sizeMainArray[j].equals(sizeMainArray[i])
							&& !processed[j]) {
						totalQty += qtyMainArray[j];
						amountForSize += amountMainArray[j];
						processed[j] = true;
					}
				}

				System.out.println("\t|\t" + sizeMainArray[i] + "\t|\t" + totalQty + "\t|\t" + amountForSize + "\t|");
				System.out.println("\t|               |               |               |");

				totalAmount += amountForSize;
			}
		}

		if (found) {
			System.out.println("\t+---------------+---------------+---------------+");
			System.out.println("\t|\tTotal Amount\t\t|\t" + totalAmount + "\t|");
			System.out.println("\t+-------------------------------+---------------+");
			System.out.print("\n\nDo you want to search another customer report? (Y/N): ");
			char config = scanner.next().charAt(0);
			reDirection(config, "searchCustomer");
		} else {
			System.out.println("\n\tInvalid input..");
			System.out.print("\n\nDo you want to search another customer report? (Y/N): ");
			char config = scanner.next().charAt(0);
			reDirection(config, "searchCustomer");
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
