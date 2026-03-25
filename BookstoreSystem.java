import java.util.Scanner;

public class BookstoreSystem {
    static Scanner sc = new Scanner(System.in);
    
    static Book[] inventory = new Book[100];
    static int invCount = 0;
    
    static Order[] orders = new Order[100];
    static int ordCount = 0;

    static int nextB = 1, nextO = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- SIMPLE BOOKSTORE ---");
            System.out.println("1. New Book (B" + nextB + ")");
            System.out.println("2. New Order (ORD" + nextO + ")");
            System.out.println("3. Change Status");
            System.out.println("4. List Inventory");
            System.out.println("5. List Orders");
            System.out.println("6. Exit");
            System.out.print("Chọn (1-6): ");
            
            String choice = sc.nextLine();

            if (choice.equals("1")) addBook();
            else if (choice.equals("2")) createOrder();
            else if (choice.equals("3")) editStatus();
            else if (choice.equals("4")) showBooks();
            else if (choice.equals("5")) showOrders();
            else if (choice.equals("6")) break;
        }
    }

    static void addBook() {
        String id = "B" + nextB++;
        System.out.print("Title: ");  String t = sc.nextLine();
        System.out.print("Author: "); String a = sc.nextLine();
        System.out.print("Price: ");      
        String pStr = sc.nextLine();
        double price = 0;
        try {
            price = pStr.isEmpty() ? 0 : Double.parseDouble(pStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price. Setting to 0.");
        }
        
        System.out.print("Stock: ");      
        String qStr = sc.nextLine();
        int qty = 0;
        try {
            qty = qStr.isEmpty() ? 0 : Integer.parseInt(qStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid stock. Setting to 0.");
        }
        
        inventory[invCount++] = new Book(id, t, a, price, qty);
        System.out.println("Book added successfully!");
    }

    static void createOrder() {
        String id = "ORD" + nextO++;
        System.out.print("Customer: "); String c = sc.nextLine();
        System.out.print("Address: ");  String a = sc.nextLine();
        Order o = new Order(id, c, a);
        
        while (true) {
            System.out.print("Add Book ID (type 'done'): ");
            String bId = sc.nextLine();
            if (bId.equals("done")) break;
            
            Book found = null;
            for (int i = 0; i < invCount; i++) {
                if (inventory[i].id.equals(bId)) { found = inventory[i]; break; }
            }

            if (found != null) {
                System.out.print("Qty? ");
                int q = Integer.parseInt(sc.nextLine());
                if (q <= found.quantity) {
                    o.books.addLast(found);
                    found.quantity -= q;
                    System.out.println("Ok.");
                } else System.out.println("No stock.");
            } else System.out.println("No ID.");
        }
        orders[ordCount++] = o;
    }

    static void editStatus() {
        System.out.print("Order ID: ");
        String id = sc.nextLine();
        for (int i = 0; i < ordCount; i++) {
            if (orders[i].orderId.equals(id)) {
                System.out.println("Current: " + orders[i].status);
                System.out.print("New (1: Shipped, 2: Delivered): ");
                String val = sc.nextLine();
                if (val.equals("1")) orders[i].status = "Shipped";
                else if (val.equals("2")) orders[i].status = "Delivered";
                break;
            }
        }
    }

    static void showBooks() {
        for (int i = 0; i < invCount; i++) System.out.println(inventory[i]);
    }

    static void showOrders() {
        System.out.println("\n--- ORDER LIST ---");
        for (int i = 0; i < ordCount; i++) {
            Order o = orders[i];
            double total = 0;
            for (int j = 0; j < o.books.size; j++) {
                Book b = o.books.getAt(j);
                total += (b.price * b.quantity);
            }
            System.out.println(o + " | TOTAL: $" + total);
        }
    }
}
