import java.util.Arrays;
import java.util.Comparator;

public class LibraryManagement {

    // LINEAR SEARCH by title — O(n)
    public static Book linearSearch(Book[] books, String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title))
                return b;
        }
        return null;
    }

    // BINARY SEARCH by title — O(log n) — needs sorted array
    public static Book binarySearch(Book[] books, String title) {
        int low = 0, high = books.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = books[mid].getTitle().compareToIgnoreCase(title);
            if (cmp == 0)     return books[mid];
            else if (cmp < 0) low  = mid + 1;
            else              high = mid - 1;
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println("=== Library Management System ===\n");

        Book[] books = {
            new Book(1, "Java Programming",   "James Gosling"),
            new Book(2, "Clean Code",         "Robert Martin"),
            new Book(3, "Design Patterns",    "Gang of Four"),
            new Book(4, "Algorithms",         "Thomas Cormen"),
            new Book(5, "Spring Boot",        "Craig Walls"),
        };

        // LINEAR SEARCH
        System.out.println("--- Linear Search ---");
        Book b1 = linearSearch(books, "Clean Code");
        System.out.println("Search 'Clean Code'   : " + 
            (b1 != null ? b1 : "Not Found"));

        Book b2 = linearSearch(books, "Python");
        System.out.println("Search 'Python'       : " + 
            (b2 != null ? b2 : "Not Found"));

        // Sort for binary search
        Arrays.sort(books, Comparator.comparing(
            b -> b.getTitle().toLowerCase()));

        // BINARY SEARCH
        System.out.println("\n--- Binary Search ---");
        Book b3 = binarySearch(books, "Algorithms");
        System.out.println("Search 'Algorithms'   : " + 
            (b3 != null ? b3 : "Not Found"));

        Book b4 = binarySearch(books, "MongoDB");
        System.out.println("Search 'MongoDB'      : " + 
            (b4 != null ? b4 : "Not Found"));

        // ANALYSIS
        System.out.println("\n--- Time Complexity ---");
        System.out.println("Linear Search → O(n)       — no sorting needed");
        System.out.println("Binary Search → O(log n)   — needs sorted array");
        System.out.println("Use Linear for small/unsorted, Binary for large/sorted!");
    }
}