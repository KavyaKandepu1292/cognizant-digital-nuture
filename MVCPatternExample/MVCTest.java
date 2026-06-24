public class MVCTest {

    public static void main(String[] args) {

        System.out.println("=== MVC Pattern Demo ===\n");

        // Step 1: Create Model (data)
        Student student = new Student("Kavya", 1001, "A");

        // Step 2: Create View (display)
        StudentView view = new StudentView();

        // Step 3: Create Controller (connects Model + View)
        StudentController controller = new StudentController(student, view);

        // Step 4: Display initial details
        view.displayMessage("Initial Student Details:");
        controller.updateView();

        System.out.println();

        // Step 5: Update student data via Controller
        view.displayMessage("Updating student details...");
        controller.setName("Kavya Kandepu");
        controller.setGrade("A+");
        controller.setId(1002);

        System.out.println();

        // Step 6: Display updated details
        view.displayMessage("Updated Student Details:");
        controller.updateView();

        System.out.println();

        // Step 7: Another student
        Student student2 = new Student("Rahul", 1003, "B+");
        StudentController controller2 = new StudentController(student2, view);

        view.displayMessage("New Student Details:");
        controller2.updateView();
    }
}