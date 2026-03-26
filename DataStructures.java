class Node {
    Book data;
    Node next;
    Node(Book b) { this.data = b; }
}

class LinkedList {
    Node head;
    int size = 0;

    void addLast(Book b) {
        Node newNode = new Node(b);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
        size++;
    }

    Book getAt(int index) {
        Node temp = head;
        if (temp == null) return null;
        for (int i = 0; i < index; i++) {
            if (temp.next == null) return null;
            temp = temp.next;
        }
        return temp.data;
    }

    void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) return;
        Node nodeI = head;
        for (int a = 0; a < i; a++) nodeI = nodeI.next;
        
        Node nodeJ = head;
        for (int a = 0; a < j; a++) nodeJ = nodeJ.next;
        
        Book temp = nodeI.data;
        nodeI.data = nodeJ.data;
        nodeJ.data = temp;
    }
}

class OrderItemNode {
    OrderItem data;
    OrderItemNode next;
    OrderItemNode(OrderItem item) { this.data = item; }
}

class OrderItemList {
    OrderItemNode head;
    int size = 0;

    void addLast(OrderItem item) {
        OrderItemNode newNode = new OrderItemNode(item);
        if (head == null) {
            head = newNode;
        } else {
            OrderItemNode temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
        size++;
    }

    OrderItem getAt(int index) {
        OrderItemNode temp = head;
        if (temp == null) return null;
        for (int i = 0; i < index; i++) {
            if (temp.next == null) return null;
            temp = temp.next;
        }
        return temp.data;
    }
}

class Queue {
    Order[] items = new Order[100];
    int front = 0, rear = 0, count = 0;

    void enqueue(Order o) {
        if (rear < items.length) {
            items[rear++] = o;
            count++;
        }
    }

    Order dequeue() {
        if (count == 0) return null;
        count--;
        return items[front++];
    }

    boolean isEmpty() { return count == 0; }
}

class Stack {
    String[] items = new String[100];
    int top = -1;

    void push(String action) {
        if (top < 99) items[++top] = action;
    }

    String pop() {
        if (top == -1) return "No history";
        return items[top--];
    }

    boolean isEmpty() { return top == -1; }
}
