// PayPalGateway.java
// This is PayPal's OWN class — we cannot change it
// It has its OWN method name: makePayment()

public class PayPalGateway {

    public void makePayment(double amount) {
        System.out.println("PayPal Gateway: Processing payment of $" 
                            + amount + " via PayPal");
    }
}