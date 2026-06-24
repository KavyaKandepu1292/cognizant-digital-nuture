public class RemoteControl {

    private Command command;
    private Command lastCommand;  // for undo

    // set which command to run
    public void setCommand(Command command) {
        this.command = command;
    }

    // press button → execute command
    public void pressButton() {
        command.execute();
        lastCommand = command;  // remember for undo
    }

    // press undo button
    public void pressUndo() {
        if (lastCommand != null) {
            System.out.println("↩ Undoing last command...");
            lastCommand.undo();
        } else {
            System.out.println("Nothing to undo!");
        }
    }
}