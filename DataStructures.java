// This file contains all the Data Structures for our system

class Node {
    Book data;
    Node next;
    Node(Book b) { this.data = b; }
}

class LinkedList {
    Node head;
    int size = 0;

    void addLast(Book b) {
        Node newNode = new Node(b);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
        size++;
    }

    Book getAt(int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) temp = temp.next;
        return temp.data;
    }
}

class Queue {
    Order[] items = new Order[100];
    int front = 0, rear = 0, count = 0;

    void enqueue(Order o) {
        items[rear++] = o;
        count++;
    }

    Order dequeue() {
        count--;
        return items[front++];
    }
}

class Stack {
    String[] history = new String[100];
    int top = -1;

    void push(String act) {
        history[++top] = act;
    }

    String pop() {
        return history[top--];
    }
}
