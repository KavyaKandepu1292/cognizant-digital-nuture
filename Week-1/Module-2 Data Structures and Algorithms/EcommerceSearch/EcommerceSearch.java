import java.util.Arrays;
import java.util.Comparator;

public class EcommerceSearch {

    // LINEAR SEARCH — O(n)
    // Checks every element one by one
    public static Product linearSearch(Product[] products, String name) {
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // BINARY SEARCH — O(log n)
    // Array MUST be sorted first!
    public static Product binarySearch(Product[] products, String name) {
        int low  = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = products[mid].getProductName()
                                   .compareToIgnoreCase(name);
            if (cmp == 0)      return products[mid]; // found!
            else if (cmp < 0)  low  = mid + 1;       // go right
            else               high = mid - 1;       // go left
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println("=== E-commerce Search Demo ===\n");

        // Unsorted array for linear search
        Product[] products = {
            new Product(101, "Laptop",     "Electronics"),
            new Product(102, "Shoes",      "Fashion"),
            new Product(103, "Phone",      "Electronics"),
            new Product(104, "Backpack",   "Accessories"),
            new Product(105, "Headphones", "Electronics"),
            new Product(106, "Watch",      "Accessories"),
        };

        // LINEAR SEARCH
        System.out.println("--- Linear Search ---");
        Product result1 = linearSearch(products, "Phone");
        System.out.println("Search 'Phone'    : " + 
            (result1 != null ? result1 : "Not Found"));

        Product result2 = linearSearch(products, "Tablet");
        System.out.println("Search 'Tablet'   : " + 
            (result2 != null ? result2 : "Not Found"));

        // Sort array for binary search
        Arrays.sort(products, 
            Comparator.comparing(p -> p.getProductName().toLowerCase()));

        System.out.println("\n--- Binary Search (sorted array) ---");
        Product result3 = binarySearch(products, "Laptop");
        System.out.println("Search 'Laptop'   : " + 
            (result3 != null ? result3 : "Not Found"));

        Product result4 = binarySearch(products, "Camera");
        System.out.println("Search 'Camera'   : " + 
            (result4 != null ? result4 : "Not Found"));

        // TIME COMPLEXITY
        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("Linear Search → Best: O(1) | Avg: O(n) | Worst: O(n)");
        System.out.println("Binary Search → Best: O(1) | Avg: O(log n) | Worst: O(log n)");
        System.out.println("Binary Search is FASTER but needs sorted array!");
    }
}