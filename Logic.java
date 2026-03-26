class Logic {

    static Book[] inventory = new Book[100];
    static int invCount = 0;
    static Order[] orders = new Order[100];
    static int ordCount = 0;
    static int nextB = 1, nextO = 1;
    static Stack history = new Stack();


    static void sortBooks(LinkedList list) {
        for (int i = 1; i < list.size; i++) {
            int j = i;
            while (j > 0 && list.getAt(j - 1).title.compareToIgnoreCase(list.getAt(j).title) > 0) {
                list.swap(j, j - 1);
                j--;
            }
        }
    }


    static void addBook(String t, String a, double price, int qty) {
        String id = "B" + nextB++;
        inventory[invCount++] = new Book(id, t, a, price, qty);
        System.out.println("Book added successfully: " + id + " [" + t + "]");
        history.push("Added book: " + t);
    }

    static void createOrder(Order o) {
        orders[ordCount++] = o;
        System.out.println("Order created successfully: " + o.orderId);
        history.push("Created order: " + o.orderId + " for " + o.customer);
    }

    static void deleteBook(String id) {
        int foundIdx = -1;
        for (int i = 0; i < invCount; i++) {
            if (inventory[i].id.equals(id)) {
                foundIdx = i;
                break;
            }
        }

        if (foundIdx != -1) {
            String title = inventory[foundIdx].title;
            for (int i = foundIdx; i < invCount - 1; i++) {
                inventory[i] = inventory[i + 1];
            }
            inventory[--invCount] = null;
            System.out.println("Book deleted successfully: " + id + " [" + title + "]");
            history.push("Deleted book: " + title);
        } else {
            System.out.println("Delete failed: Book ID " + id + " not found.");
        }
    }

    static void sortInventory() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < invCount; i++)
            list.addLast(inventory[i]);
        sortBooks(list);

        for (int i = 0; i < invCount; i++)
            inventory[i] = list.getAt(i);
        System.out.println("Inventory sorting complete (Insertion Sort).");
        history.push("Sorted inventory alphabetically.");
    }



    static void initData() {
        inventory[invCount++] = new Book("B" + nextB++, "Java Core", "James Gosling", 50.0, 10);
        inventory[invCount++] = new Book("B" + nextB++, "Clean Code", "Robert Martin", 40.0, 5);
        inventory[invCount++] = new Book("B" + nextB++, "The Hobbit", "J.R.R. Tolkien", 25.0, 8);
        inventory[invCount++] = new Book("B" + nextB++, "Python Crash Course", "Eric Matthes", 35.0, 12);
        inventory[invCount++] = new Book("B" + nextB++, "Design Patterns", "Gang of Four", 55.0, 3);
        inventory[invCount++] = new Book("B" + nextB++, "1984", "George Orwell", 12.0, 15);
        inventory[invCount++] = new Book("B" + nextB++, "Effective Java", "Joshua Bloch", 45.0, 7);
        inventory[invCount++] = new Book("B" + nextB++, "The Great Gatsby", "F. Scott Fitzgerald", 15.0, 20);
        inventory[invCount++] = new Book("B" + nextB++, "To Kill a Mockingbird", "Harper Lee", 18.0, 10);
        inventory[invCount++] = new Book("B" + nextB++, "Brave New World", "Aldous Huxley", 13.0, 22);
        Order o1 = new Order("ORD" + nextO++, "Victor Hugo", "123 London St");
        o1.items.addLast(new OrderItem(inventory[0], 1));
        o1.items.addLast(new OrderItem(inventory[2], 1));
        orders[ordCount++] = o1;
        Order o2 = new Order("ORD" + nextO++, "Alice Smith", "456 New York Ave");
        o2.items.addLast(new OrderItem(inventory[1], 2));
        orders[ordCount++] = o2;
        Order o3 = new Order("ORD" + nextO++, "John Doe", "789 Broadway");
        o3.items.addLast(new OrderItem(inventory[3], 1));
        o3.items.addLast(new OrderItem(inventory[4], 1));
        o3.items.addLast(new OrderItem(inventory[5], 1));
        orders[ordCount++] = o3;
    }
}
