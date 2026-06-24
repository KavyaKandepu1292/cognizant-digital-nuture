public class PaymentContext {

    // holds current payment strategy
    private PaymentStrategy paymentStrategy;

    // set strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // execute whichever strategy is set
    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            System.out.println(" No payment strategy selected!");
            return;
        }
        paymentStrategy.pay(amount);
    }
}