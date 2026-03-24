class Algorithms {

    // Bubble Sort algorithm: simplest sorting for beginners
    // It compares adjacent elements and swaps them if they are in the wrong order
    public static void sortBooks(LinkedList list) {
        int n = list.size;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Get two adjacent books to compare their titles
                String title1 = list.getAt(j).title;
                String title2 = list.getAt(j + 1).title;

                // If title1 should come after title2 alphabetically
                if (title1.compareTo(title2) > 0) {
                    // Swap them to organize the order
                    list.swap(j, j + 1);
                }
            }
        }
    }

    // Linear Search algorithm: steps through the list to find a match
    public static Order findOrder(Order[] history, int count, String id) {
        for (int i = 0; i < count; i++) {
            // Check if the current order ID matches the search ID
            if (history[i].orderId.equals(id)) {
                return history[i]; // Return the order if found
            }
        }
        return null; // Return null if the order is not found after searching everyone
    }
}
