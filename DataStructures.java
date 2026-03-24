// Custom Linked List to store books dynamically
class LinkedList {
    // Inner class representing a single node (link) in the chain
    class Node {
        Book data;
        Node next;
        Node(Book b) { this.data = b; }
    }
    
    Node head; // The start of the list
    int size = 0;

    // Add a book to the end of the list
    public void addLast(Book b) {
        Node newNode = new Node(b);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    // Get the book at a specific position
    public Book getAt(int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) temp = temp.next;
        return temp.data;
    }

    // Swap two books in the list by swapping their values
    public void swap(int i, int j) {
        Node tempI = head;
        for (int a = 0; a < i; a++) tempI = tempI.next;
        
        Node tempJ = head;
        for (int a = 0; a < j; a++) tempJ = tempJ.next;
        
        Book dataI = tempI.data;
        tempI.data = tempJ.data;
        tempJ.data = dataI;
    }
}

// Custom Queue to manage order processing (FIFO)
class Queue {
    Order[] items;
    int front = 0, rear = 0, count = 0;
    
    public Queue() { items = new Order[100]; }
    
    // Add an order to the end of the queue
    public void enqueue(Order o) {
        items[rear++] = o;
        count++;
    }
    
    // Remove and return the order from the front of the queue
    public Order dequeue() {
        count--;
        return items[front++];
    }
    
    // Check if the queue is empty
    public boolean isEmpty() { return count == 0; }
}
