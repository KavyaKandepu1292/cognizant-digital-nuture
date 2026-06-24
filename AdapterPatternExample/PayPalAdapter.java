// PayPalAdapter.java
// ADAPTER — bridges app and PayPal
// Implements PaymentProcessor (interface)
// Internally uses PayPalGateway (interface)

public class PayPalAdapter implements PaymentProcessor {

    // holds reference to PayPal's gateway
    private PayPalGateway payPalGateway;

    public PayPalAdapter() {
        this.payPalGateway = new PayPalGateway();
    }

    @Override
    public void processPayment(double amount) {
        // YOUR app calls processPayment()
        // Adapter translates it to PayPal's makePayment()
        System.out.println("PayPalAdapter: Converting processPayment → makePayment");
        payPalGateway.makePayment(amount);
    }
}