class Order {
    String orderId;
    String customer;
    String address;
    String status;

    // We use the LinkedList from DataStructures.java
    LinkedList books;

    Order(String id, String customer, String address) {
        this.orderId = id;
        this.customer = customer;
        this.address = address;
        this.status = "Pending";
        this.books = new LinkedList();
    }

    public String toString() {
        return "Order: " + orderId + " | Customer: " + customer + " | Status: " + status;
    }
}
