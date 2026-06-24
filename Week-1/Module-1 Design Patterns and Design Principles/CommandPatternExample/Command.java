public interface Command {
    void execute();
    void undo();   // bonus: undo the command
}