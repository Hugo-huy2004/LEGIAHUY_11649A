// Main application to demonstrate the Bookstore system
public class Main {
    public static void main(String[] args) {
        System.out.println("=== ONLINE BOOKSTORE MANAGEMENT SYSTEM ===\n");

        // 1. Initialize the queue and history collection
        Queue queue = new Queue();
        Order[] history = new Order[100];
        int historyCount = 0;

        // 2. Add new orders (Enqueuing)
        Order o1 = new Order("ORD-001", "Alice");
        o1.addBook(new Book("Java Fundamentals", "Author X", 15.0));
        o1.addBook(new Book("Algorithms 101", "Author Y", 25.0));
        
        queue.enqueue(o1);
        history[historyCount++] = o1;

        System.out.println("New order received: " + o1.orderId);

        // 3. Process orders (Dequeuing and Sorting)
        System.out.println("\n--- Processing orders from the queue ---");
        while (!queue.isEmpty()) {
            Order processing = queue.dequeue();
            
            // Organize books in the order alphabetically
            Algorithms.sortBooks(processing.bookList);

            System.out.println("Order " + processing.orderId + " setup complete!");
            for (int i = 0; i < processing.bookList.size; i++) {
                System.out.println("  + " + processing.bookList.getAt(i));
            }
            processing.status = "Delivered";
        }

        // 4. Track orders (Searching)
        System.out.println("\n--- Tracking specific order ---");
        System.out.println("Entering ID to search: ORD-001");
        Order found = Algorithms.findOrder(history, historyCount, "ORD-001");
        if (found != null) {
            System.out.println("Customer Name: " + found.customerName);
            System.out.println("Current Status: " + found.status);
        }
    }
}
