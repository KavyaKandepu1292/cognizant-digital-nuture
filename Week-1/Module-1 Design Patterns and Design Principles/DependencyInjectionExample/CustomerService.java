// CustomerService.java
// Service class — contains business logic
// DEPENDS on CustomerRepository
// Repository is INJECTED via constructor (Dependency Injection!)

public class CustomerService {

    // Dependency — CustomerRepository
    private CustomerRepository customerRepository;

    // CONSTRUCTOR INJECTION
    // Repository is passed from outside — not created here!
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Business method: find customer
    public Customer getCustomer(int id) {
        System.out.println("Searching for customer with ID: " + id);
        Customer customer = customerRepository.findCustomerById(id);
        if (customer != null) {
            System.out.println("Found: " + customer);
        } else {
            System.out.println("Customer not found with ID: " + id);
        }
        return customer;
    }

    // Business method: add new customer
    public void addNewCustomer(int id, String name, String email) {
        Customer customer = new Customer(id, name, email);
        customerRepository.addCustomer(customer);
    }

    // Business method: remove customer
    public void removeCustomer(int id) {
        customerRepository.deleteCustomer(id);
    }
}