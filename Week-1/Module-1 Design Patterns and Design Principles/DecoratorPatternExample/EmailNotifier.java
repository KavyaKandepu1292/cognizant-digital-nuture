// EmailNotifier.java
// BASE class — the core notification (always runs first)

public class EmailNotifier implements Notifier {

    @Override
    public void send(String message) {
        System.out.println(" Email Notification sent: " + message);
    }
}