class OrdersCollection {
    private Order[] orderArray;

    OrdersCollection(){
        orderArray = new Order[0];    
    }

    public boolean addOrder(Order order) {
        Order[] tempOrderArray = new Order[orderArray.length + 1];

        for (int i = 0; i < orderArray.length; i++) {
            tempOrderArray[i] = orderArray[i];
        }

        tempOrderArray[orderArray.length] = order;
        orderArray = tempOrderArray;
        return true;
    }

    public Order[] searchCustomerID(String custId) {
        int count = 0;
        for (Order order : orderArray) {
            if (order.getCustomerID().equalsIgnoreCase(custId)) {
                count++;
            }
        }
        if (count == 0) {
            return new Order[0];
        }
        Order[] foundOrders = new Order[count];
        int index = 0;
        for (Order order : orderArray) {
            if (order.getCustomerID().equalsIgnoreCase(custId)) {
                foundOrders[index++] = order;
            }
        }
        return foundOrders;
    }
    
    public Order[] searchOrderID(String orderID) {
        int count = 0;
        for (Order order : orderArray) {
            if (order.getOrderId().equalsIgnoreCase(orderID)) {
                count++;
            }
        }
        if (count == 0) {
            return new Order[0];
        }
        Order[] foundOrders = new Order[count];
        int index = 0;
        for (Order order : orderArray) {
            if (order.getOrderId().equalsIgnoreCase(orderID)) {
                foundOrders[index++] = order;
            }
        }
        return foundOrders;
    }
    

}
