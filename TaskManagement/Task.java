public class Task {
    int    taskId;
    String taskName;
    String status;
    Task   next;     // pointer to next node

    public Task(int taskId, String taskName, String status) {
        this.taskId   = taskId;
        this.taskName = taskName;
        this.status   = status;
        this.next     = null;
    }

    @Override
    public String toString() {
        return "Task[ID=" + taskId + ", Name=" + taskName 
               + ", Status=" + status + "]";
    }
}