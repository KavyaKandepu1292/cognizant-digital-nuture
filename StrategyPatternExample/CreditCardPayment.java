public class CreditCardPayment implements PaymentStrategy {

    private String cardNumber;
    private String cardHolder;

    public CreditCardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public void pay(double amount) {
        System.out.println(" Credit Card Payment:");
        System.out.println("   Card Holder : " + cardHolder);
        System.out.println("   Card Number : " + cardNumber);
        System.out.println("   Amount Paid : $" + amount);
    }
}