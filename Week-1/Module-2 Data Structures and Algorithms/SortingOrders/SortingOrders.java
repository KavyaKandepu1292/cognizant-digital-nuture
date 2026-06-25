public class SortingOrders {

    // BUBBLE SORT — O(n²)
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j+1].getTotalPrice()) {
                    // swap
                    Order temp   = orders[j];
                    orders[j]    = orders[j+1];
                    orders[j+1]  = temp;
                }
            }
        }
    }

    // QUICK SORT — O(n log n) average
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low,    pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                Order temp  = orders[i];
                orders[i]   = orders[j];
                orders[j]   = temp;
            }
        }
        Order temp      = orders[i+1];
        orders[i+1]     = orders[high];
        orders[high]    = temp;
        return i + 1;
    }

    public static void printOrders(Order[] orders) {
        for (Order o : orders) System.out.println("  " + o);
    }

    public static Order[] getSampleOrders() {
        return new Order[]{
            new Order(1, "Kavya",  1500.00),
            new Order(2, "Rahul",  450.50),
            new Order(3, "Priya",  8999.00),
            new Order(4, "Arjun",  220.00),
            new Order(5, "Sneha",  3200.75),
            new Order(6, "Kiran",  750.00),
        };
    }

    public static void main(String[] args) {

        System.out.println("=== Sorting Customer Orders ===\n");

        // BUBBLE SORT
        Order[] orders1 = getSampleOrders();
        System.out.println("--- Before Bubble Sort ---");
        printOrders(orders1);
        bubbleSort(orders1);
        System.out.println("--- After Bubble Sort (asc price) ---");
        printOrders(orders1);

        System.out.println();

        // QUICK SORT
        Order[] orders2 = getSampleOrders();
        System.out.println("--- Before Quick Sort ---");
        printOrders(orders2);
        quickSort(orders2, 0, orders2.length - 1);
        System.out.println("--- After Quick Sort (asc price) ---");
        printOrders(orders2);

        // ANALYSIS
        System.out.println("\n--- Time Complexity ---");
        System.out.println("Bubble Sort → O(n²)       — slow for large data");
        System.out.println("Quick Sort  → O(n log n)  — fast, preferred!");
    }
}