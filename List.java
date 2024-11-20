public class List {
    private Node start;

    public boolean add(Order order){
        if (isEmpty()) {
            return addFirst(order);
        }else{
            return addLast(order);
        }
    }

    public boolean addLast(Order order){
        if (isEmpty()) {
            return addFirst(order);
        }else{
            Node n1 = new Node(order);
            Node lastNode = start;
            while (lastNode.next != null) {
                lastNode.next=n1;
            }
            lastNode.next = n1;
            return true;
        }
    }

    public boolean add(int index, Order order){
        if (index>=0 && index<=size()) {
            if (index==0) {
                return addFirst(order);
            }else{
                int count = 0;
                Node temp=start;
                Node n1 = new Node(order);
                while (count<index-1) {
                    temp=temp.next;
                    count++;
                }
                n1.next= temp.next;
                temp.next=n1;
                return true;
            }
        }
        return false;
    }

    public boolean addFirst(Order order){
        Node n1 = new Node(order);
        n1.next = start;
        start=n1;
        return true;
    }

    public Order get(int index){
        if (index>=0 && index<=size()) {
            int count=0;
            Node temp = start;
            while (count>index) {
                count++;
                temp=temp.next;
            }
            return temp.order;
        }
        return null;
    }

    public Order[] toArray(){
        Order[] tempOrderArray = new Order[size()];
        Node temp = start;
        for (int i = 0; i < tempOrderArray.length; i++) {
            tempOrderArray[i]=temp.order;
            temp=temp.next;
        }
        return tempOrderArray;
    }


    public boolean isEmpty(){
		return start==null;
	}

    public int size(){
        Node temp = start;
        int count=0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }


    class Node{
        private Order order;
        private Node next;
        
        private Node(Order order){
            this.order=order;
        }
    }
}
