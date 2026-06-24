public class NetBankingPayment implements PaymentStrategy {

    private String bankName;
    private String accountNumber;

    public NetBankingPayment(String bankName, String accountNumber) {
        this.bankName      = bankName;
        this.accountNumber = accountNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println(" Net Banking Payment:");
        System.out.println("   Bank        : " + bankName);
        System.out.println("   Account No  : " + accountNumber);
        System.out.println("   Amount Paid : $" + amount);
    }
}