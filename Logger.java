// Logger.java
public class Logger {

    // The one and only Logger that will ever exist.
    // Stored here at the class level so it survives across method calls.
    // Starts as null — we create it lazily, only when first needed.
    private static Logger instance;

    // No one outside this class can call new Logger().
    // That's intentional — we want all creation to go through getInstance().
    private Logger() {
        System.out.println("Logger instance created!");
    }

    // The single entry point for getting a Logger.
    // Safe to call from anywhere without an existing Logger object,
    // because `static` means it belongs to the class itself.
    public static Logger getInstance() {
        if (instance == null) {
            // First call ever — spin one up and remember it.
            instance = new Logger();
        }
        // Every subsequent call skips the if and returns the same object.
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}