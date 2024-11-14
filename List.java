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
}