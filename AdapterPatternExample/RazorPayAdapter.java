// RazorPayAdapter.java

public class RazorPayAdapter implements PaymentProcessor {

    private RazorPayGateway razorPayGateway;

    public RazorPayAdapter() {
        this.razorPayGateway = new RazorPayGateway();
    }

    @Override
    public void processPayment(double amount) {
        // Translates processPayment() → sendMoney()
        System.out.println("RazorPayAdapter: Converting processPayment → sendMoney");
        razorPayGateway.sendMoney(amount);
    }
}