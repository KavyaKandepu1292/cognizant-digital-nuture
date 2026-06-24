public class StrategyTest {

    public static void main(String[] args) {

        System.out.println("=== Strategy Pattern Demo ===\n");

        // Create context — no strategy set yet
        PaymentContext context = new PaymentContext();

        // Payment 1 — use Credit Card
        System.out.println("--- Order 1: Paying with Credit Card ---");
        context.setPaymentStrategy(
            new CreditCardPayment("1234-5678-9012-3456", "Kavya")
        );
        context.executePayment(1500.00);

        System.out.println();

        // Payment 2 — switch to PayPal at runtime!
        System.out.println("--- Order 2: Paying with PayPal ---");
        context.setPaymentStrategy(
            new PayPalPayment("kavya@gmail.com")
        );
        context.executePayment(299.99);

        System.out.println();

        // Payment 3 — switch to Net Banking at runtime!
        System.out.println("--- Order 3: Paying with Net Banking ---");
        context.setPaymentStrategy(
            new NetBankingPayment("SBI Bank", "SBI9876543210")
        );
        context.executePayment(5000.00);

        System.out.println();

        // Payment 4 — switch back to Credit Card
        System.out.println("--- Order 4: Back to Credit Card ---");
        context.setPaymentStrategy(
            new CreditCardPayment("9999-8888-7777-6666", "Kavya")
        );
        context.executePayment(750.50);
    }
}