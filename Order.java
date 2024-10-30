public class Order {
    private String orderId;
    private String size;
    private int quantity;
    private double amount;
    private String customerID;
    private String orderStatus;

    public Order() {
    }

    public Order(String orderId, String size, int quantity, double amount, String customerID, String orderStatus) {
        this.orderId = orderId;
        this.size = size;
        this.quantity = quantity;
        this.amount = amount;
        this.customerID = customerID;
        this.orderStatus = orderStatus;
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
}
