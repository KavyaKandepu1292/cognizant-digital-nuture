// StripeAdapter.java

public class StripeAdapter implements PaymentProcessor {

    private StripeGateway stripeGateway;

    public StripeAdapter() {
        this.stripeGateway = new StripeGateway();
    }

    @Override
    public void processPayment(double amount) {
        // Translates processPayment() to charge()
        System.out.println("StripeAdapter: Converting processPayment → charge");
        stripeGateway.charge(amount);
    }
}