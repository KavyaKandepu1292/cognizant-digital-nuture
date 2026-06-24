// NotifierDecorator.java
// Abstract Decorator — middle layer
// Holds reference to any Notifier object
// All concrete decorators extend this

public abstract class NotifierDecorator implements Notifier {

    // This holds the wrapped notifier (Email or another decorator)
    protected Notifier wrappedNotifier;

    // Constructor takes any Notifier to wrap around
    public NotifierDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }

    // Delegates send() to the wrapped notifier
    @Override
    public void send(String message) {
        wrappedNotifier.send(message);
    }
}