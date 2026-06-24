// CustomerRepositoryImpl.java
// Concrete implementation of CustomerRepository
// Acts like a fake database using a List

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    // In-memory list acts as database
    private List<Customer> database = new ArrayList<>();

    public CustomerRepositoryImpl() {
        // Pre-load some customers
        database.add(new Customer(101, "Kavya",  "kavya@gmail.com"));
        database.add(new Customer(102, "Rahul",  "rahul@gmail.com"));
        database.add(new Customer(103, "Priya",  "priya@gmail.com"));
        database.add(new Customer(104, "Arjun",  "arjun@gmail.com"));
    }

    @Override
    public Customer findCustomerById(int id) {
        for (Customer customer : database) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;  // not found
    }

    @Override
    public void addCustomer(Customer customer) {
        database.add(customer);
        System.out.println("Customer added: " + customer);
    }

    @Override
    public void deleteCustomer(int id) {
        database.removeIf(c -> c.getId() == id);
        System.out.println("Customer with ID " + id + " deleted.");
    }
}