// DependencyInjectionTest.java
// Main test class

public class DependencyInjectionTest {

    public static void main(String[] args) {

        System.out.println("=== Dependency Injection Demo ===\n");

        // Step 1: Create the Repository (concrete implementation)
        CustomerRepository repository = new CustomerRepositoryImpl();

        // Step 2: INJECT repository into service via constructor
        // CustomerService doesn't create its own repo — we give it from outside!
        CustomerService service = new CustomerService(repository);

        // Step 3: Find existing customers
        System.out.println("--- Finding Customers ---");
        service.getCustomer(101);
        System.out.println();
        service.getCustomer(103);
        System.out.println();

        // Step 4: Find non-existing customer
        System.out.println("--- Finding Non-existing Customer ---");
        service.getCustomer(999);
        System.out.println();

        // Step 5: Add new customer
        System.out.println("--- Adding New Customer ---");
        service.addNewCustomer(105, "Sneha", "sneha@gmail.com");
        System.out.println();

        // Step 6: Verify new customer added
        System.out.println("--- Verifying New Customer ---");
        service.getCustomer(105);
        System.out.println();

        // Step 7: Delete a customer
        System.out.println("--- Deleting Customer ---");
        service.removeCustomer(102);
        System.out.println();

        // Step 8: Verify deletion
        System.out.println("--- Verifying Deletion ---");
        service.getCustomer(102);
    }
}