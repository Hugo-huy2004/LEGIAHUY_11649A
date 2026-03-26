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

    @Override
    public String toString() {
        return "[" + id + "] " + title + " - Price: $" + price + " - Stock: " + quantity;
    }
}

class OrderItem {
    Book book;
    int qty;

    OrderItem(Book book, int qty) {
        this.book = book;
        this.qty = qty;
    }
}

class Order {
    String orderId;
    String customer;
    String address;
    String status;
    OrderItemList items;

    Order(String id, String customer, String address) {
        this.orderId = id;
        this.customer = customer;
        this.address = address;
        this.status = "Pending";
        this.items = new OrderItemList();
    }

    @Override
    public String toString() {
        return "Order: " + orderId + " | Customer: " + customer + " | Status: " + status;
    }
}
