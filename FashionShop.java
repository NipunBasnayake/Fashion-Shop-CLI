class FashionShop {
	public static void main(String args[]) {
		OrdersCollection ordersCollection = new OrdersCollection();
		new HomeWindow(ordersCollection).setVisible(true);

	}
}