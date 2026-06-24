// SlackNotifierDecorator.java
// Concrete Decorator — ADDS Slack on top of existing notifications

public class SlackNotifierDecorator extends NotifierDecorator {

    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        // First call the wrapped notifier (Email + SMS run first)
        wrappedNotifier.send(message);

        // Then ADD Slack on top
        System.out.println("Slack Notification sent: " + message);
    }
}