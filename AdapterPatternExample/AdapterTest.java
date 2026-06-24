// AdapterTest.java

public class AdapterTest {

    public static void main(String[] args) {

        System.out.println("=== Adapter Pattern Demo ===\n");

        // app only knows PaymentProcessor
        // It doesn't care which gateway is behind!

        PaymentProcessor payment;

        // Pay via PayPal
        System.out.println("--- Paying via PayPal ---");
        payment = new PayPalAdapter();
        payment.processPayment(150.00);

        System.out.println();

        // Pay via Stripe
        System.out.println("--- Paying via Stripe ---");
        payment = new StripeAdapter();
        payment.processPayment(299.99);

        System.out.println();

        // Pay via RazorPay
        System.out.println("--- Paying via RazorPay ---");
        payment = new RazorPayAdapter();
        payment.processPayment(500.00);
    }
}