// StripeGateway.java
// Stripe's OWN class — cannot change it
// Has its OWN method: charge()

public class StripeGateway {

    public void charge(double amount) {
        System.out.println("Stripe Gateway: Charging $" 
                            + amount + " via Stripe");
    }
}