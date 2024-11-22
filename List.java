public class List {
    private Node start;
    private int listSize = 0;

    public boolean add(Order order) {
        if (isEmpty()) {
            return addFirst(order);
        } else {
            return addLast(order);
        }
    }

    public boolean addLast(Order order) {
        if (isEmpty()) {
            return addFirst(order);
        } else {
            Node n1 = new Node(order);
            Node lastNode = start;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = n1;
            listSize++;
            return true;
        }
    }

    public boolean add(int index, Order order) {
        if (index >= 0 && index <= listSize) {
            if (index == 0) {
                return addFirst(order);
            } else {
                int count = 0;
                Node temp = start;
                Node n1 = new Node(order);
                while (count < index - 1) {
                    temp = temp.next;
                    count++;
                }
                n1.next = temp.next;
                temp.next = n1;
                listSize++;
                return true;
            }
        }
        return false;
    }

    public boolean addFirst(Order order) {
        Node n1 = new Node(order);
        n1.next = start;
        start = n1;
        listSize++;
        return true;
    }

    public int searchById(String orderId) {
        Node temp = start;
        int count = 0;
        while (temp != null) {
            if (temp.order.getOrderId().equalsIgnoreCase(orderId)) {
                return count;
            }
            count++;
            temp = temp.next;
        }
        return -1;
    }

    public int searchByOrder(Order order) {
        Node temp = start;
        int count = 0;
        while (temp != null) {
            if (temp.order.getOrderId().equals(order.getOrderId())) {
                return count;
            }
            count++;
            temp = temp.next;
        }
        return -1;
    }

    public Order get(int index) {
        if (index >= 0 && index < listSize) {
            int count = 0;
            Node temp = start;
            while (count < index) {
                count++;
                temp = temp.next;
            }
            return temp.order;
        }
        return null;
    }

    public boolean set(int index, Order order) {
        if (index >= 0 && index < size()) {
            Node temp = start;
            int count = 0;
            while (count < index) {
                count++;
                temp = temp.next;
            }
            temp.order = order;
            return true;
        }
        return false;
    }

    public Order[] toArray() {
        Order[] tempOrderArray = new Order[listSize];
        Node temp = start;
        for (int i = 0; i < tempOrderArray.length; i++) {
            tempOrderArray[i] = temp.order;
            temp = temp.next;
        }
        return tempOrderArray;
    }

    public boolean isEmpty() {
        return start == null;
    }

    public int size() {
        return listSize;
    }

    public Order removeFirst() {
        if (!isEmpty()) {
            Order c1 = start.order;
            start = start.next;
            listSize--;
            return c1;
        }
        return null;
    }

    public Order removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (start.next == null) {
            Order customer = start.order;
            start = null;
            listSize--;
            return customer;
        }
        Node temp = start;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        Order customer = temp.next.order;
        temp.next = null;
        listSize--;
        return customer;
    }

    public Order remove(int index) {
        if (index >= 0 && index < size()) {
            if (index == 0) {
                return removeFirst();
            }
            Node temp = start;
            int count = 0;
            while (count < index - 1) {
                temp = temp.next;
                count++;
            }
            Order customer = temp.next.order;
            temp.next = temp.next.next;
            listSize--;
            return customer;
        }
        return null;
    }

    public boolean remove(Order order) {
        int index = searchByOrder(order);
        return remove(index) != null;
    }

    class Node {
        private Order order;
        private Node next;

        private Node(Order order) {
            this.order = order;
        }
    }
}
