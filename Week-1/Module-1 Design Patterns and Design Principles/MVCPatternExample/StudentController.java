// CONTROLLER — bridge between Model and View
// Gets data from Model, sends to View
public class StudentController {

    private Student student;      // reference to Model
    private StudentView view;     // reference to View

    public StudentController(Student student, StudentView view) {
        this.student = student;
        this.view    = view;
    }

    // Get from Model
    public String getName()  { return student.getName();  }
    public int    getId()    { return student.getId();    }
    public String getGrade() { return student.getGrade(); }

    // Set to Model
    public void setName(String name)   { student.setName(name);   }
    public void setId(int id)          { student.setId(id);       }
    public void setGrade(String grade) { student.setGrade(grade); }

    // Tell View to display current Model data
    public void updateView() {
        view.displayStudentDetails(
            student.getName(),
            student.getId(),
            student.getGrade()
        );
    }
}