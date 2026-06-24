// SingletonTest.java
public class SingletonTest {
    public static void main(String[] args) {

        // Simulate three different parts of the app each grabbing the logger.
        // In a real codebase these would be separate classes — here we're
        // just proving that getInstance() hands back the same object every time.
        Logger logger1 = Logger.getInstance();
        logger1.log("Application started");

        Logger logger2 = Logger.getInstance();
        logger2.log("User logged in");

        Logger logger3 = Logger.getInstance();
        logger3.log("Data saved successfully");

        // == on objects checks identity, not equality — it's true only if
        // both variables point to the exact same spot in memory.
        // If the singleton is working, all three should pass.
        System.out.println("\n--- Verification ---");
        System.out.println("logger1 == logger2: " + (logger1 == logger2));
        System.out.println("logger2 == logger3: " + (logger2 == logger3));

        // hashCode() is a proxy for memory address here.
        // Three different variables, but one number — that's the singleton guarantee.
        System.out.println("\nlogger1 hashCode: " + logger1.hashCode());
        System.out.println("logger2 hashCode: " + logger2.hashCode());
        System.out.println("logger3 hashCode: " + logger3.hashCode());
    }
}