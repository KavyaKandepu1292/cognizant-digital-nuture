public class InventoryTest {

    public static void main(String[] args) {

        System.out.println("Inventory Management System \n");

        Inventory inventory = new Inventory();

        // ADD products
        System.out.println("--- Adding Products ---");
        inventory.addProduct(new Product(101, "Laptop",       50,  75000.00));
        inventory.addProduct(new Product(102, "Mouse",        200, 599.00));
        inventory.addProduct(new Product(103, "Keyboard",     150, 1299.00));
        inventory.addProduct(new Product(104, "Monitor",      30,  18000.00));
        inventory.addProduct(new Product(105, "USB Hub",      100, 899.00));

        // DISPLAY all
        System.out.println("\n--- Current Inventory ---");
        inventory.displayAll();

        // SEARCH
        System.out.println("\n--- Searching Products ---");
        inventory.searchProduct(103);
        inventory.searchProduct(999);  // not found

        // UPDATE
        System.out.println("\n--- Updating Product ---");
        inventory.updateProduct(102, "Wireless Mouse", 180, 799.00);

        // DELETE
        System.out.println("\n--- Deleting Product ---");
        inventory.deleteProduct(105);

        // ADD duplicate test
        System.out.println("\n--- Adding Duplicate ---");
        inventory.addProduct(new Product(101, "Laptop Duplicate", 10, 50000.00));

        // DISPLAY final
        System.out.println("\n--- Final Inventory ---");
        inventory.displayAll();

        // TIME COMPLEXITY ANALYSIS
        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("Add    → O(1) — HashMap.put()");
        System.out.println("Search → O(1) — HashMap.get()");
        System.out.println("Update → O(1) — HashMap.get() + modify");
        System.out.println("Delete → O(1) — HashMap.remove()");
        System.out.println("Display→ O(n) — iterate all entries");
    }
}