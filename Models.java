// Represents a single book in the store
class Book {
    String title;
    String author;
    double price;

    // Constructor to initialize book details
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Convert book details to a readable string format
    public String toString() {
        return title + " - " + author;
    }
}

// Represents a customer's order containing multiple books
class Order {
    String orderId;
    String customerName;
    LinkedList bookList; // Custom linked list to store books
    String status;

    // Constructor to initialize order details
    public Order(String orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.bookList = new LinkedList(); 
        this.status = "Pending";
    }

    // Add a book to the order's book list
    public void addBook(Book b) {
        bookList.addLast(b);
    }
}
