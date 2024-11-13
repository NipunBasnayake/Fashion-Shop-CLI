public class List {
    private Order[] orderArray;
    private int nextIndex;
    private double loadFactor;
    private int initSize;

    public List(int initSize, double loadFactor) {
        this.initSize = initSize;
        orderArray = new Order[initSize];
        nextIndex = 0;
        this.loadFactor = loadFactor;
    }

    public boolean add(Order newOrder) {
        if (nextIndex >= orderArray.length) {
            extendArray();
        }
        orderArray[nextIndex++] = newOrder;
        return true;
    }

    public void clear() {
        orderArray = new Order[initSize];
        nextIndex = 0;
        System.out.println("List cleared");
    }

    private void extendArray() {
        int newSize = (int) (orderArray.length * (1 + loadFactor));
        Order[] newArray = new Order[newSize];
        System.arraycopy(orderArray, 0, newArray, 0, orderArray.length);
        orderArray = newArray;
        System.out.println("Array extended to size " + newSize);
    }

    // ------------------- Get Methods -------------------

    public Order get(int index) {
        if (index >= 0 && index < nextIndex) {
            return orderArray[index];
        }
        return null;
    }

    // ------------------- Remove Methods -------------------

    public void remove(int index) {
        if (index >= 0 && index < nextIndex) {
            for (int i = index; i < nextIndex - 1; i++) {
                orderArray[i] = orderArray[i + 1];
            }
            orderArray[nextIndex - 1] = null;
            nextIndex--;
        }
    }

    public boolean deleteOrder(String orderID) {
        for (int i = 0; i < nextIndex; i++) {
            if (orderArray[i] != null && orderID.equalsIgnoreCase(orderArray[i].getOrderId())) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    // ------------------- Search Methods -------------------

    public int search(Order order) {
        for (int i = 0; i < nextIndex - 1; i++) {
            if (orderArray[i].equals(order)) {
                return i;
            }
        }
        return -1;
    }

    public int size(){
        return nextIndex;
    }

    public Order[] getOrderArray() {
        if (orderArray == null) {
            throw new IllegalStateException("Order array is null");
        }

        Order[] copyOrderArray = new Order[orderArray.length];
        for (int i = 0; i < orderArray.length; i++) {
            copyOrderArray[i] = orderArray[i];
        }
        return copyOrderArray;
    }

    // public Order[] searchCustomerID(String custId) {
    //     int count = 0;
    //     for (Order order : orderArray) {
    //         if (order != null && order.getCustomerID().equalsIgnoreCase(custId)) {
    //             count++;
    //         }
    //     }
    //     if (count == 0) {
    //         return new Order[0];
    //     }
    //     Order[] foundOrders = new Order[count];
    //     int index = 0;
    //     for (Order order : orderArray) {
    //         if (order != null && order.getCustomerID().equalsIgnoreCase(custId)) {
    //             foundOrders[index++] = order;
    //         }
    //     }
    //     return foundOrders;
    // }

    // public Order[] searchOrderID(String orderID) {
    //     int count = 0;
    //     for (Order order : orderArray) {
    //         if (order != null && order.getOrderId().equalsIgnoreCase(orderID)) {
    //             count++;
    //         }
    //     }
    //     if (count == 0) {
    //         return new Order[0];
    //     }
    //     Order[] foundOrders = new Order[count];
    //     int index = 0;
    //     for (Order order : orderArray) {
    //         if (order != null && order.getOrderId().equalsIgnoreCase(orderID)) {
    //             foundOrders[index++] = order;
    //         }
    //     }
    //     return foundOrders;
    // }

    // public Order[] viewCustomers() {
    //     Order[] viewCustomers = new Order[orderArray.length];
    //     boolean[] equalPass = new boolean[orderArray.length];
    //     int count = 0;

    //     for (int i = 0; i < orderArray.length; i++) {
    //         if (equalPass[i] || orderArray[i] == null) {
    //             continue;
    //         }

    //         viewCustomers[count] = new Order();
    //         int tempQty = orderArray[i].getQuantity();
    //         double tempAmount = orderArray[i].getAmount();
    //         equalPass[i] = true;

    //         for (int j = i + 1; j < orderArray.length; j++) {
    //             if (orderArray[j] != null && orderArray[i].getCustomerID().equals(orderArray[j].getCustomerID())) {
    //                 tempQty += orderArray[j].getQuantity();
    //                 tempAmount += orderArray[j].getAmount();
    //                 equalPass[j] = true;
    //             }
    //         }

    //         String cusPhoneNumber = orderArray[i].getCustomerID();
    //         viewCustomers[count].setCustomerID(cusPhoneNumber);
    //         viewCustomers[count].setQuantity(tempQty);
    //         viewCustomers[count].setAmount(tempAmount);
    //         count++;
    //     }
    //     return viewCustomers;
    // }

    // public Order[] bestInCustomers() {
    //     Order[] viewBestCustomers = new Order[orderArray.length];
    //     boolean[] equalPass = new boolean[orderArray.length];
    //     int count = 0;

    //     for (int i = 0; i < orderArray.length; i++) {
    //         if (equalPass[i] || orderArray[i] == null) {
    //             continue;
    //         }

    //         viewBestCustomers[count] = new Order();
    //         int tempQty = orderArray[i].getQuantity();
    //         double tempAmount = orderArray[i].getAmount();
    //         equalPass[i] = true;

    //         for (int j = i + 1; j < orderArray.length; j++) {
    //             if (orderArray[j] != null && orderArray[i].getCustomerID().equals(orderArray[j].getCustomerID())) {
    //                 tempQty += orderArray[j].getQuantity();
    //                 tempAmount += orderArray[j].getAmount();
    //                 equalPass[j] = true;
    //             }
    //         }

    //         String customerPhoneNumber = orderArray[i].getCustomerID();
    //         viewBestCustomers[count].setCustomerID(customerPhoneNumber);
    //         viewBestCustomers[count].setQuantity(tempQty);
    //         viewBestCustomers[count].setAmount(tempAmount);
    //         count++;
    //     }

    //     for (int i = count - 1; i > 0; i--) {
    //         for (int j = 0; j < i; j++) {
    //             if (viewBestCustomers[j] != null && viewBestCustomers[j + 1] != null) {
    //                 if (viewBestCustomers[j].getAmount() < viewBestCustomers[j + 1].getAmount()) {
    //                     Order temp = viewBestCustomers[j];
    //                     viewBestCustomers[j] = viewBestCustomers[j + 1];
    //                     viewBestCustomers[j + 1] = temp;
    //                 }
    //             }
    //         }
    //     }

    //     return viewBestCustomers;
    // }

    // public Order[] allCustomers() {
    //     if (orderArray == null) {
    //         throw new IllegalStateException("Order array is null");
    //     }

    //     Order[] orders = new Order[orderArray.length];
    //     boolean[] processed = new boolean[orderArray.length];
    //     int count = 0;

    //     for (int i = 0; i < orderArray.length; i++) {
    //         if (processed[i] || orderArray[i] == null) {
    //             continue;
    //         }

    //         String customerId = orderArray[i].getCustomerID();
    //         if (customerId == null) {
    //             continue;
    //         }

    //         Order aggregatedOrder = new Order();
    //         aggregatedOrder.setCustomerID(customerId);
    //         double totalAmount = 0;
    //         int xsQty = 0, sQty = 0, mQty = 0, lQty = 0, xlQty = 0, xxlQty = 0;

    //         for (int j = i; j < orderArray.length; j++) {
    //             if (orderArray[j] != null && customerId.equals(orderArray[j].getCustomerID())) {
    //                 String size = orderArray[j].getSize();
    //                 if (size != null) {
    //                     switch (size) {
    //                         case "XS":
    //                             xsQty += orderArray[j].getQuantity();
    //                             break;
    //                         case "S":
    //                             sQty += orderArray[j].getQuantity();
    //                             break;
    //                         case "M":
    //                             mQty += orderArray[j].getQuantity();
    //                             break;
    //                         case "L":
    //                             lQty += orderArray[j].getQuantity();
    //                             break;
    //                         case "XL":
    //                             xlQty += orderArray[j].getQuantity();
    //                             break;
    //                         case "XXL":
    //                             xxlQty += orderArray[j].getQuantity();
    //                             break;
    //                     }
    //                 }
    //                 totalAmount += orderArray[j].getAmount();
    //                 processed[j] = true;
    //             }
    //         }

    //         aggregatedOrder.setXtraSmall(xsQty);
    //         aggregatedOrder.setSmall(sQty);
    //         aggregatedOrder.setMediumSize(mQty);
    //         aggregatedOrder.setLarge(lQty);
    //         aggregatedOrder.setXtraLarge(xlQty);
    //         aggregatedOrder.setXtraXl(xxlQty);
    //         aggregatedOrder.setAmount(totalAmount);

    //         orders[count++] = aggregatedOrder;
    //     }

    //     Order[] result = new Order[count];
    //     System.arraycopy(orders, 0, result, 0, count);
    //     return result;
    // }

    // public Order[] sortByQty() {
    //     if (orderArray == null) {
    //         throw new IllegalStateException("Order array is null");
    //     }

    //     Order[] items = new Order[orderArray.length];
    //     int uniqueSizesCount = 0;

    //     for (Order order : orderArray) {
    //         if (order == null) {
    //             continue;
    //         }

    //         String size = order.getSize();
    //         int qty = order.getQuantity();
    //         double amount = order.getAmount();

    //         boolean isNewSize = true;
    //         int sizeIndex = -1;

    //         for (int i = 0; i < uniqueSizesCount; i++) {
    //             if (size != null && items[i] != null && items[i].getSize().equals(size)) {
    //                 isNewSize = false;
    //                 sizeIndex = i;
    //                 break;
    //             }
    //         }

    //         if (isNewSize) {
    //             Order newItem = new Order();
    //             newItem.setSize(size);
    //             newItem.setQuantity(qty);
    //             newItem.setAmount(amount);
    //             items[uniqueSizesCount] = newItem;
    //             uniqueSizesCount++;
    //         } else {
    //             items[sizeIndex].setQuantity(items[sizeIndex].getQuantity() + qty);
    //             items[sizeIndex].setAmount(items[sizeIndex].getAmount() + amount);
    //         }
    //     }

    //     for (int i = 0; i < uniqueSizesCount - 1; i++) {
    //         for (int j = 0; j < uniqueSizesCount - i - 1; j++) {
    //             if (items[j].getQuantity() < items[j + 1].getQuantity()) {
    //                 Order temp = items[j];
    //                 items[j] = items[j + 1];
    //                 items[j + 1] = temp;
    //             }
    //         }
    //     }
    //     Order[] result = new Order[uniqueSizesCount];
    //     System.arraycopy(items, 0, result, 0, uniqueSizesCount);
    //     return result;
    // }

    // public Order[] sortByAmount() {
    //     if (orderArray == null) {
    //         throw new IllegalStateException("Order array is null");
    //     }

    //     Order[] items = new Order[orderArray.length];
    //     int uniqueSizesCount = 0;

    //     for (Order order : orderArray) {
    //         if (order == null) {
    //             continue;
    //         }

    //         String size = order.getSize();
    //         int qty = order.getQuantity();
    //         double amount = order.getAmount();

    //         boolean isNewSize = true;
    //         int sizeIndex = -1;

    //         for (int i = 0; i < uniqueSizesCount; i++) {
    //             if (size != null && items[i] != null && items[i].getSize().equals(size)) {
    //                 isNewSize = false;
    //                 sizeIndex = i;
    //                 break;
    //             }
    //         }

    //         if (isNewSize) {
    //             Order newItem = new Order();
    //             newItem.setSize(size);
    //             newItem.setQuantity(qty);
    //             newItem.setAmount(amount);
    //             items[uniqueSizesCount] = newItem;
    //             uniqueSizesCount++;
    //         } else {
    //             items[sizeIndex].setQuantity(items[sizeIndex].getQuantity() + qty);
    //             items[sizeIndex].setAmount(items[sizeIndex].getAmount() + amount);
    //         }
    //     }

    //     for (int i = 0; i < uniqueSizesCount - 1; i++) {
    //         for (int j = 0; j < uniqueSizesCount - i - 1; j++) {
    //             if (items[j].getAmount() < items[j + 1].getAmount()) {
    //                 Order temp = items[j];
    //                 items[j] = items[j + 1];
    //                 items[j + 1] = temp;
    //             }
    //         }
    //     }
    //     Order[] result = new Order[uniqueSizesCount];
    //     System.arraycopy(items, 0, result, 0, uniqueSizesCount);
    //     return result;
    // }

    // public Order[] ordersByAmount() {
    //     if (orderArray == null) {
    //         throw new IllegalStateException("Order array is null");
    //     }

    //     Order[] sortByAmountArray = new Order[orderArray.length];
    //     for (int i = 0; i < orderArray.length; i++) {
    //         sortByAmountArray[i] = orderArray[i];
    //     }

    //     for (int i = orderArray.length - 1; i > 0; i--) {
    //         for (int j = 0; j < i; j++) {
    //             if (sortByAmountArray[j] != null && sortByAmountArray[j + 1] != null &&
    //                     sortByAmountArray[j].getAmount() < sortByAmountArray[j + 1].getAmount()) {

    //                 Order temp = sortByAmountArray[j];
    //                 sortByAmountArray[j] = sortByAmountArray[j + 1];
    //                 sortByAmountArray[j + 1] = temp;
    //             }
    //         }
    //     }
    //     return sortByAmountArray;
    // }



    // public void setOrderStatus(int status, String orderId) {
    //     for (int i = 0; i < orderArray.length; i++) {
    //         if (orderArray[i].getOrderId() != null && orderId.equals(orderArray[i].getOrderId())) {
    //             if (status == 1) {
    //                 orderArray[i].setOrderStatus("Delivering");
    //                 break;
    //             } else if (status == 2) {
    //                 orderArray[i].setOrderStatus("Delivered");
    //                 break;
    //             }
    //         }
    //     }
    // }

    // public int changeOrderStatus(String id) {
    //     for (int i = 0; i < orderArray.length; i++) {
    //         if (orderArray[i].getOrderId() != null && id.equalsIgnoreCase(orderArray[i].getOrderId())) {
    //             if (orderArray[i].getOrderStatus().equalsIgnoreCase("Processing")) {
    //                 return 0;
    //             } else if (orderArray[i].getOrderStatus().equalsIgnoreCase("Delivering")) {
    //                 return 1;
    //             } else if (orderArray[i].getOrderStatus().equalsIgnoreCase("Delivered")) {
    //                 return 2;
    //             }
    //         }
    //     }
    //     return -1;
    // }



}
