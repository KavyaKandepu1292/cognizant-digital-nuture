// RazorPayGateway.java
// RazorPay's OWN class — cannot change it
// Has its OWN method: sendMoney()

public class RazorPayGateway {

    public void sendMoney(double amount) {
        System.out.println("RazorPay Gateway: Sending $" 
                            + amount + " via RazorPay");
    }
}