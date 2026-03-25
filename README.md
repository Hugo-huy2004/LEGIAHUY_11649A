# Online Bookstore Order Processing System

## Student Information
- **Name:** LE GIA HUY
- **Student ID:** GCS230377
- **Course:** Data Structures and Algorithms (DSA)

---

## Project Overview
This project is a simplified **Online Bookstore System** built using Java. It follows a modular architecture that separates data models, custom data structures, and the main system logic. The goal is to efficiently manage book inventory and customer orders using fundamental computer science concepts.

## Key Features
1.  **Inventory Management**: Add and search for books with automated ID generation (`B1`, `B2`, etc.).
2.  **Order Processing**: Create customer orders by selecting books directly from the inventory using their IDs.
3.  **Real-time Stock Tracking**: The system automatically deducts stock when an order is placed and prevents orders that exceed available quantity.
4.  **Order Status Management**: Track and update order statuses (Pending, Shipped, Delivered).
5.  **Automated Pricing**: Real-time calculation of order totals based on unit price and quantity.

## Data Structures & Algorithms
- **LinkedList**: Used for storing dynamic lists of books within customer orders.
- **Queue (FIFO)**: Managed for processing orders in the order they were received.
- **Stack (LIFO)**: Implemented for tracking system action history.
- **Sorting (Bubble Sort)**: Organizes the inventory alphabetically by book title.
- **Searching (Linear Search)**: Efficiently locates books and orders by their unique IDs.

## File Structure
- `src/BookstoreSystem.java`: The main entry point containing the menu and core logic.
- `src/Book.java`: Data model for book information.
- `src/Order.java`: Data model for customer order details.
- `src/DataStructures.java`: Implementation of custom LinkedList, Queue, and Stack.

## How to Run
Requirement: Java Development Kit (JDK) installed.

1. Open your terminal in the project root directory.
2. Compile all source files:
   ```bash
   javac src/*.java
   ```
3. Run the system:
   ```bash
   java -cp src BookstoreSystem
   ```
