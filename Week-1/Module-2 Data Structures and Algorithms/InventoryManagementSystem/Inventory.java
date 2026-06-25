import java.util.HashMap;
import java.util.Map;

public class Inventory {

    // HashMap: key = productId, value = Product
    // O(1) for all operations!
    private HashMap<Integer, Product> inventory = new HashMap<>();

    // ADD product — O(1)
    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product ID " + product.getProductId() 
                                + " already exists!");
            return;
        }
        inventory.put(product.getProductId(), product);
        System.out.println("Product added: " + product.getProductName());
    }

    //  UPDATE product — O(1)
    public void updateProduct(int productId, String newName,
                               int newQty, double newPrice) {
        if (!inventory.containsKey(productId)) {
            System.out.println(" Product ID " + productId + " not found!");
            return;
        }
        Product product = inventory.get(productId);  // O(1)
        product.setProductName(newName);
        product.setQuantity(newQty);
        product.setPrice(newPrice);
        System.out.println("Product updated: ID = " + productId);
    }

    // DELETE product — O(1)
    public void deleteProduct(int productId) {
        if (!inventory.containsKey(productId)) {
            System.out.println(" Product ID " + productId + " not found!");
            return;
        }
        String name = inventory.get(productId).getProductName();
        inventory.remove(productId);  // O(1)
        System.out.println("🗑 Product deleted: " + name);
    }

    // SEARCH product — O(1)
    public void searchProduct(int productId) {
        if (!inventory.containsKey(productId)) {
            System.out.println(" Product ID " + productId + " not found!");
            return;
        }
        System.out.println(" Found: " + inventory.get(productId));
    }

    //  DISPLAY all products — O(n)
    public void displayAll() {
        if (inventory.isEmpty()) {
            System.out.println(" Inventory is empty!");
            return;
        }
       
        System.out.println("| ID    | Name                 | Quantity | Price    |");
        
        for (Map.Entry<Integer, Product> entry : inventory.entrySet()) {
            System.out.println(entry.getValue());
        }
        
        System.out.println("Total Products: " + inventory.size());
    }
}