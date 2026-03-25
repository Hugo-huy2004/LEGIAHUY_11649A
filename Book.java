class Book {
    String id;
    String title;
    String author;
    double price;
    int quantity;

    Book(String id, String title, String author, double price, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString() {
        return "[" + id + "] " + title + " - Price: $" + price + " - Stock: " + quantity;
    }
}
