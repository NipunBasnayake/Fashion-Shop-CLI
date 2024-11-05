import java.util.Arrays;

class OrdersCollection {
    private Order[] orderArray;

    OrdersCollection() {
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

    public Order[] viewCustomers() {
        Order[] viewCustomers = new Order[orderArray.length];
        boolean[] equalPass = new boolean[orderArray.length];
        int count = 0;

        for (int i = 0; i < orderArray.length; i++) {
            if (equalPass[i]) {
                continue;
            }
            viewCustomers[count] = new Order();
            int tempQty = orderArray[i].getQuantity();
            double tempAmount = orderArray[i].getAmount();
            equalPass[i] = true;

            for (int j = i + 1; j < orderArray.length; j++) {
                if (orderArray[i].getCustomerID().equals(orderArray[j].getCustomerID())) {
                    tempQty += orderArray[j].getQuantity();
                    tempAmount += orderArray[j].getAmount();
                    equalPass[j] = true;
                }
            }

            String cusPhoneNumber = orderArray[i].getCustomerID();
            viewCustomers[count].setCustomerID(cusPhoneNumber);
            viewCustomers[count].setQuantity(tempQty);
            viewCustomers[count].setAmount(tempAmount);
            count++;
        }
        return viewCustomers;
    }

    public Order[] bestInCustomers() {
        Order[] viewBestCustomers = new Order[orderArray.length];
        boolean[] equalPass = new boolean[orderArray.length];
        int count = 0;

        for (int i = 0; i < orderArray.length; i++) {
            if (equalPass[i]) {
                continue;
            }

            viewBestCustomers[count] = new Order();

            int tempqty = orderArray[i].getQuantity();
            double tempAmount = orderArray[i].getAmount();
            equalPass[i] = true;

            for (int j = i + 1; j < orderArray.length; j++) {
                if (orderArray[i].getCustomerID().equals(orderArray[j].getCustomerID())) {
                    tempqty += orderArray[j].getQuantity();
                    tempAmount += orderArray[j].getAmount();
                    equalPass[j] = true;
                }
            }
            String customerPhoneNumber = orderArray[i].getCustomerID();

            viewBestCustomers[count].setCustomerID(customerPhoneNumber);
            viewBestCustomers[count].setQuantity(i);
            viewBestCustomers[count].setAmount(tempAmount);
            count++;
        }
        for (int i = count - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (viewBestCustomers[j] != null && viewBestCustomers[j + 1] != null) {
                    if (viewBestCustomers[j].getQuantity() != 0 && viewBestCustomers[j + 1].getQuantity() != 0) {
                        if (viewBestCustomers[j].getAmount() < viewBestCustomers[j + 1].getAmount()) {
                            Order swap = viewBestCustomers[j];
                            viewBestCustomers[j] = viewBestCustomers[j + 1];
                            viewBestCustomers[j + 1] = swap;
                        }
                    }

                }
            }
        }
        return viewBestCustomers;
    }

    public Order[] allCustomers() {
        Order[] aggregatedOrders = new Order[orderArray.length];
        boolean[] processed = new boolean[orderArray.length];
        int count = 0;

        for (int i = 0; i < orderArray.length; i++) {
            if (processed[i])
                continue;

            String customerId = orderArray[i].getCustomerID();
            Order aggregatedOrder = new Order();
            aggregatedOrder.setCustomerID(customerId);
            double totalAmount = 0;
            int xsQty = 0, sQty = 0, mQty = 0, lQty = 0, xlQty = 0, xxlQty = 0;

            for (int j = i; j < orderArray.length; j++) {
                if (orderArray[j].getCustomerID().equals(customerId)) {
                    switch (orderArray[j].getSize()) {
                        case "XS":
                            xsQty += orderArray[j].getQuantity();
                            break;
                        case "S":
                            sQty += orderArray[j].getQuantity();
                            break;
                        case "M":
                            mQty += orderArray[j].getQuantity();
                            break;
                        case "L":
                            lQty += orderArray[j].getQuantity();
                            break;
                        case "XL":
                            xlQty += orderArray[j].getQuantity();
                            break;
                        case "XXL":
                            xxlQty += orderArray[j].getQuantity();
                            break;
                    }
                    totalAmount += orderArray[j].getAmount();
                    processed[j] = true;
                }
            }
            aggregatedOrder.setXtraSmall(xsQty);
            aggregatedOrder.setSmall(sQty);
            aggregatedOrder.setMediumSize(mQty);
            aggregatedOrder.setLarge(lQty);
            aggregatedOrder.setXtraLarge(xlQty);
            aggregatedOrder.setXtraXl(xxlQty);
            aggregatedOrder.setAmount(totalAmount);

            aggregatedOrders[count++] = aggregatedOrder;
        }
        return Arrays.copyOf(aggregatedOrders, count);
    }
}
