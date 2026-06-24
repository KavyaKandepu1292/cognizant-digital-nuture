// SMSNotifierDecorator.java
// Concrete Decorator — ADDS SMS on top of existing notification

public class SMSNotifierDecorator extends NotifierDecorator {

    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);  // pass to parent (NotifierDecorator)
    }

    @Override
    public void send(String message) {
        // First call the wrapped notifier (Email runs first)
        wrappedNotifier.send(message);

        // Then ADD SMS on top
        System.out.println("SMS Notification sent: " + message);
    }
}