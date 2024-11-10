public class Order {
    private String orderId;
    private String size;
    private int quantity;
    private double amount;
    private String customerID;
    private String orderStatus;

    private int xtraSmall;
    private int small;
    private int mediumSize;
    private int large;
    private int xtraLarge;
    private int xtraXl;

    public Order() {}

    public Order(String orderId, String size, int quantity, double amount, String customerID, String orderStatus) {
        this.orderId = orderId;
        this.size = size;
        this.quantity = quantity;
        this.amount = amount;
        this.customerID = customerID;
        this.orderStatus = orderStatus;
    }

    public int getXtraSmall() {
        return xtraSmall;
    }

    public void setXtraSmall(int xtraSmall) {
        this.xtraSmall = xtraSmall;
    }

    public int getSmall() {
        return small;
    }

    public void setSmall(int small) {
        this.small = small;
    }

    public int getMediumSize() {
        return mediumSize;
    }

    public void setMediumSize(int mediumSize) {
        this.mediumSize = mediumSize;
    }

    public int getLarge() {
        return large;
    }

    public void setLarge(int large) {
        this.large = large;
    }

    public int getXtraLarge() {
        return xtraLarge;
    }

    public void setXtraLarge(int xtraLarge) {
        this.xtraLarge = xtraLarge;
    }

    public int getXtraXl() {
        return xtraXl;
    }

    public void setXtraXl(int xtraXl) {
        this.xtraXl = xtraXl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean equals(Order order){
        return this.orderId.equalsIgnoreCase(order.orderId);
    }

    public String toString(){
		return orderId+","+size+","+quantity+","+amount+","+customerID+","+orderStatus;
	}
}