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

    public Order[] sortByQty() {
        Order[] items = new Order[orderArray.length];
        int uniqueSizesCount = 0;

        for (Order order : orderArray) {
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
                Order newItem = new Order();
                newItem.setSize(size);
                newItem.setQuantity(qty);
                newItem.setAmount(amount);
                items[uniqueSizesCount] = newItem;
                uniqueSizesCount++;
            } else {
                items[sizeIndex].setQuantity(items[sizeIndex].getQuantity() + qty);
                items[sizeIndex].setAmount(items[sizeIndex].getAmount() + amount);
            }
        }

        for (int i = 0; i < uniqueSizesCount - 1; i++) {
            for (int j = 0; j < uniqueSizesCount - i - 1; j++) {
                if (items[j].getQuantity() < items[j + 1].getQuantity()) {
                    Order temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
        return Arrays.copyOf(items, uniqueSizesCount);
    }

    public Order[] sortByAmount() {
        Order[] items = new Order[orderArray.length];
        int uniqueSizesCount = 0;

        for (Order order : orderArray) {
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
                Order newItem = new Order();
                newItem.setSize(size);
                newItem.setQuantity(qty);
                newItem.setAmount(amount);
                items[uniqueSizesCount] = newItem;
                uniqueSizesCount++;
            } else {
                items[sizeIndex].setQuantity(items[sizeIndex].getQuantity() + qty);
                items[sizeIndex].setAmount(items[sizeIndex].getAmount() + amount);
            }
        }

        for (int i = 0; i < uniqueSizesCount - 1; i++) {
            for (int j = 0; j < uniqueSizesCount - i - 1; j++) {
                if (items[j].getAmount() < items[j + 1].getAmount()) {
                    Order temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
        return Arrays.copyOf(items, uniqueSizesCount);
    }

    public Order[] ordersByAmount(){
        Order[] sortByAmountArray = new Order[orderArray.length];
        for(int i=0; i<orderArray.length; i++){
            sortByAmountArray[i]=orderArray[i];
        }        

        for(int i=orderArray.length-1; i>0; i--){
            for(int j=0; j<i; j++)
            if(sortByAmountArray[j].getAmount()<sortByAmountArray[j+1].getAmount()){
                Order temp=orderArray[j];
                sortByAmountArray[j]=sortByAmountArray[j+1];
                sortByAmountArray[j+1]=temp;
            }
        }
        return sortByAmountArray;
    }

    public Order[] getOrderArray(){
        Order[] copyOrderArray = new Order[orderArray.length];
        for(int i=0; i<orderArray.length; i++){
            copyOrderArray[i]=orderArray[i];            
        }
        return copyOrderArray;
    }

    public int changeOrderStatus(String id){
        for(int i=0; i<orderArray.length; i++){
            if(id.equalsIgnoreCase(orderArray[i].getOrderId())){
                if(orderArray[i].getOrderStatus().equalsIgnoreCase("Processing")){
                    return 0;
                }else if(orderArray[i].getOrderStatus().equalsIgnoreCase("Delivering")){
                    return 1;
                }else if(orderArray[i].getOrderStatus().equalsIgnoreCase("Delivered")){
                    return 2;
                }
            }            
        }
        return -1;        
    }

    public void setOrderStatus(int status,String orderId){
        for(int i=0; i<orderArray.length; i++){
            if(orderId.equals(orderArray[i].getOrderId())){
                if(status==1){
                    orderArray[i].setOrderStatus("Delivering");
                    break;                
                }else if(status==2){
                    orderArray[i].setOrderStatus("Delivered");
                    break;
                }
            } 
        }
    }

    public boolean deleteOrder(String id){
        int index=-1;
        for(int i=0; i<orderArray.length; i++){
            if(id.equalsIgnoreCase(orderArray[i].getOrderId())){
                index=i;
                break;
            }            
        }
        if(index==-1){
            return false;
        }else{
            Order[] tempCusDetails = new Order[orderArray.length-1];
            for(int i=0, j=0; i<orderArray.length; i++){
                if(i!=index){
                    tempCusDetails[j]=orderArray[i];
                    j++;
                }
            }
            orderArray=tempCusDetails;
            return true;
        }
    }
}