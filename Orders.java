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
