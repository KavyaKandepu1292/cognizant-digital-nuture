public class TaskManagement {

    private Task head;  // first node

    // ADD at end — O(n)
    public void addTask(Task task) {
        if (head == null) { head = task; }
        else {
            Task current = head;
            while (current.next != null)
                current = current.next;
            current.next = task;
        }
        System.out.println(" Task added: " + task.taskName);
    }

    // SEARCH — O(n)
    public Task searchTask(int taskId) {
        Task current = head;
        while (current != null) {
            if (current.taskId == taskId) return current;
            current = current.next;
        }
        return null;
    }

    // TRAVERSE — O(n)
    public void traverse() {
        System.out.println("\n--- Task List ---");
        Task current = head;
        int count = 0;
        while (current != null) {
            System.out.println("  " + current);
            current = current.next;
            count++;
        }
        System.out.println("Total tasks: " + count);
    }

    // DELETE — O(n)
    public void deleteTask(int taskId) {
        if (head == null) { System.out.println("List empty!"); return; }
        if (head.taskId == taskId) {
            System.out.println("Deleted: " + head.taskName);
            head = head.next;
            return;
        }
        Task current = head;
        while (current.next != null) {
            if (current.next.taskId == taskId) {
                System.out.println(" Deleted: " + current.next.taskName);
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
        System.out.println(" Task ID " + taskId + " not found!");
    }

    public static void main(String[] args) {

        System.out.println("=== Task Management System ===\n");

        TaskManagement tm = new TaskManagement();

        // ADD
        System.out.println("--- Adding Tasks ---");
        tm.addTask(new Task(1, "Design UI",      "Pending"));
        tm.addTask(new Task(2, "Write Code",     "In Progress"));
        tm.addTask(new Task(3, "Test Feature",   "Pending"));
        tm.addTask(new Task(4, "Deploy to Prod", "Pending"));

        tm.traverse();

        // SEARCH
        System.out.println("\n--- Searching ---");
        Task t = tm.searchTask(3);
        System.out.println("Search ID 3: " + (t != null ? t : "Not Found"));

        // DELETE
        System.out.println("\n--- Deleting ---");
        tm.deleteTask(2);
        tm.traverse();

        // COMPLEXITY
        System.out.println("\n--- Time Complexity ---");
        System.out.println("Add       O(n)  traverse to end");
        System.out.println("Search    O(n)");
        System.out.println("Delete    O(n)");
        System.out.println("Advantage: Dynamic size — no fixed capacity!");
    }
}