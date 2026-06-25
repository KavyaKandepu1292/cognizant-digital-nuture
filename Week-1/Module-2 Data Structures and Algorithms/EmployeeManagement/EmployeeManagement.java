public class EmployeeManagement {

    private Employee[] employees;
    private int        size;
    private int        capacity;

    public EmployeeManagement(int capacity) {
        this.capacity  = capacity;
        this.employees = new Employee[capacity];
        this.size      = 0;
    }

    // ADD — O(1)
    public void addEmployee(Employee e) {
        if (size >= capacity) {
            System.out.println(" Array full!"); return;
        }
        employees[size++] = e;
        System.out.println(" Added: " + e.getName());
    }

    // SEARCH — O(n)
    public Employee searchById(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == id)
                return employees[i];
        }
        return null;
    }

    // TRAVERSE — O(n)
    public void traverse() {
        System.out.println("\n--- All Employees ---");
        for (int i = 0; i < size; i++)
            System.out.println("  " + employees[i]);
        System.out.println("Total: " + size);
    }

    // DELETE — O(n)
    public void deleteEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == id) {
                String name = employees[i].getName();
                // shift left
                for (int j = i; j < size - 1; j++)
                    employees[j] = employees[j+1];
                employees[--size] = null;
                System.out.println("🗑 Deleted: " + name);
                return;
            }
        }
        System.out.println("Employee ID " + id + " not found!");
    }

    public static void main(String[] args) {

        System.out.println("=== Employee Management System ===\n");

        EmployeeManagement emp = new EmployeeManagement(10);

        // ADD
        System.out.println("--- Adding Employees ---");
        emp.addEmployee(new Employee(1, "Kavya",  "Developer",  75000));
        emp.addEmployee(new Employee(2, "Rahul",  "Designer",   60000));
        emp.addEmployee(new Employee(3, "Priya",  "Manager",    90000));
        emp.addEmployee(new Employee(4, "Arjun",  "Tester",     55000));

        // TRAVERSE
        emp.traverse();

        // SEARCH
        System.out.println("\n--- Searching ---");
        Employee e = emp.searchById(3);
        System.out.println("Search ID 3: " + (e != null ? e : "Not Found"));

        // DELETE
        System.out.println("\n--- Deleting ---");
        emp.deleteEmployee(2);
        emp.traverse();

        // COMPLEXITY
        System.out.println("\n--- Time Complexity ---");
        System.out.println("Add      → O(1)");
        System.out.println("Search   → O(n)");
        System.out.println("Traverse → O(n)");
        System.out.println("Delete   → O(n) — shifting required");
    }
}