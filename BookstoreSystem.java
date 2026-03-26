public class BookstoreSystem {
    static String readLine() {
        StringBuilder sb = new StringBuilder();
        try {
            int ch;
            while ((ch = System.in.read()) != '\n' && ch != -1) {
                if (ch != '\r')
                    sb.append((char) ch);
            }
        } catch (Exception e) {
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Logic.initData();
        while (true) {
            System.out.println("\n--- SIMPLE BOOKSTORE ---");
            System.out.println("1. New Book (B" + Logic.nextB + ")");
            System.out.println("2. New Order (ORD" + Logic.nextO + ")");
            System.out.println("3. Check Order Details");
            System.out.println("4. List Inventory");
            System.out.println("5. List Orders");
            System.out.println("6. Sort Inventory");
            System.out.println("7. View History");
            System.out.println("8. Delete Book");
            System.out.println("9. Exit");
            System.out.print("Select (1-9): ");

            String choice = readLine();

            if (choice.equals("1"))
                handleNewBook();
            else if (choice.equals("2"))
                handleNewOrder();
            else if (choice.equals("3"))
                handleCheckOrder();
            else if (choice.equals("4"))
                showBooks();
            else if (choice.equals("5"))
                showOrders();
            else if (choice.equals("6"))
                Logic.sortInventory();
            else if (choice.equals("7"))
                showHistory();
            else if (choice.equals("8"))
                handleDeleteBook();
            else if (choice.equals("9"))
                break;
        }
    }

    static void handleNewBook() {
        System.out.print("Title: ");
        String t = readLine();
        System.out.print("Author: ");
        String a = readLine();
        System.out.print("Price: ");
        String pStr = readLine();
        double price = 0;
        try {
            price = pStr.isEmpty() ? 0 : Double.parseDouble(pStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price. Setting to 0.");
        }

        System.out.print("Stock: ");
        String qStr = readLine();
        int qty = 0;
        try {
            qty = qStr.isEmpty() ? 0 : Integer.parseInt(qStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid stock. Setting to 0.");
        }

        Logic.addBook(t, a, price, qty);
    }

    static void handleNewOrder() {
        String id = "ORD" + Logic.nextO;
        System.out.print("Customer: ");
        String c = readLine();
        System.out.print("Address: ");
        String a = readLine();
        Order o = new Order(id, c, a);

        while (true) {
            System.out.print("Add Book ID (type 'done'): ");
            String bId = readLine();
            if (bId.equals("done"))
                break;

            Book found = null;
            for (int i = 0; i < Logic.invCount; i++) {
                if (Logic.inventory[i].id.equals(bId)) {
                    found = Logic.inventory[i];
                    break;
                }
            }

            if (found != null) {
                System.out.print("Qty? ");
                try {
                    int q = Integer.parseInt(readLine());
                    if (q > 0 && q <= found.quantity) {
                        o.items.addLast(new OrderItem(found, q));
                        found.quantity -= q;
                        System.out.println("Added to order: " + found.title + " (x" + q + ")");
                    } else {
                        System.out.println("Insufficient stock or invalid quantity for: " + bId);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity format.");
                }
            } else
                System.out.println("Book ID not found in inventory: " + bId);
        }
        Logic.createOrder(o);
    }

    static void handleCheckOrder() {
        System.out.print("Order ID to check: ");
        String id = readLine();
        for (int i = 0; i < Logic.ordCount; i++) {
            Order o = Logic.orders[i];
            if (o.orderId.equals(id)) {
                System.out.println("\n--- ORDER INFORMATION ---");
                System.out.println("ID: " + o.orderId + " | Customer: " + o.customer);
                System.out.println("Address: " + o.address);
                System.out.println("Current Status: " + o.status);
                System.out.println("Items:");
                double total = 0;
                for (int j = 0; j < o.items.size; j++) {
                    OrderItem item = o.items.getAt(j);
                    double itemTotal = item.book.price * item.qty;
                    System.out.printf("  - %-20s (x%-2d) | Total: $%-7.2f\n",
                            item.book.title, item.qty, itemTotal);
                    total += itemTotal;
                }
                System.out.println("GRAND TOTAL: $" + total);
                System.out.println("-------------------------");

                System.out.print("Update status (1: Shipped, 2: Delivered, Enter: No change): ");
                String val = readLine();
                if (val.equals("1")) {
                    o.status = "Shipped";
                    System.out.println("Order " + o.orderId + " status updated to SHIPPED.");
                } else if (val.equals("2")) {
                    o.status = "Delivered";
                    System.out.println("Order " + o.orderId + " status updated to DELIVERED.");
                } else {
                    System.out.println("No status change requested for " + o.orderId);
                }
                Logic.history.push("Updated order " + o.orderId + " to " + o.status);
                return;
            }
        }
        System.out.println("Order lookup failed: ID " + id + " not found.");
    }

    static void handleDeleteBook() {
        System.out.print("Enter Book ID to delete: ");
        String id = readLine();
        Logic.deleteBook(id);
    }

    static void showBooks() {
        System.out.println("\n--- INVENTORY LIST ---");
        System.out.printf("%-5s | %-25s | %-20s | %-8s | %-5s\n", "ID", "Title", "Author", "Price", "Stock");
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < Logic.invCount; i++) {
            Book b = Logic.inventory[i];
            System.out.printf("%-5s | %-25s | %-20s | $%-7.2f | %-5d\n", b.id, b.title, b.author, b.price, b.quantity);
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    static void showOrders() {
        System.out.println("\n--- ORDER LIST ---");
        for (int i = 0; i < Logic.ordCount; i++) {
            Order o = Logic.orders[i];
            System.out.println("-------------------------------------------");
            System.out.println("ID: " + o.orderId + " | Customer: " + o.customer);
            System.out.println("Address: " + o.address);
            System.out.println("Status: " + o.status);
            System.out.println("Items:");
            double total = 0;
            for (int j = 0; j < o.items.size; j++) {
                OrderItem item = o.items.getAt(j);
                double itemTotal = item.book.price * item.qty;
                System.out.printf("  - %-20s (x%-2d) | Unit: $%-7.2f | Total: $%-7.2f\n",
                        item.book.title, item.qty, item.book.price, itemTotal);
                total += itemTotal;
            }
            System.out.println("GRAND TOTAL: $" + total);
        }
        System.out.println("-------------------------------------------");
    }

    static void showHistory() {
        System.out.println("\n--- RECENT ACTIONS (STACK LIFO) ---");
        if (Logic.history.isEmpty()) {
            System.out.println("No actions yet.");
            return;
        }
        while (!Logic.history.isEmpty()) {
            System.out.println(" -> " + Logic.history.pop());
        }
        System.out.println("(History cleared after viewing)");
    }
}
