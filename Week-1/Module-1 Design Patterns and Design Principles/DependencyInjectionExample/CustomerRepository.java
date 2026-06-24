// CustomerRepository.java
// Repository Interface — defines what operations are available

public interface CustomerRepository {
    Customer findCustomerById(int id);
    void     addCustomer(Customer customer);
    void     deleteCustomer(int id);
}