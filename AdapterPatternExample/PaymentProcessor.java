// PaymentProcessor.java
// This is what  APP understands
// All adapters must implement this

public interface PaymentProcessor {
    void processPayment(double amount);
}