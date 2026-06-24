public class ObserverTest {

    public static void main(String[] args) {

        System.out.println("=== Observer Pattern Demo ===\n");

        // Create the subject (Stock Market)
        StockMarket googleStock = new StockMarket("GOOGLE");

        // Create observers
        Observer mobileApp1 = new MobileApp("StockTracker App");
        Observer mobileApp2 = new MobileApp("Groww App");
        Observer webApp1    = new WebApp("Trading Website");

        // Register observers
        System.out.println("--- Registering Observers ---");
        googleStock.register(mobileApp1);
        googleStock.register(mobileApp2);
        googleStock.register(webApp1);

        // Change price → all 3 get notified
        googleStock.setPrice(2850.50);
        googleStock.setPrice(2900.00);

        // Remove one observer
        System.out.println("\n--- Removing MobileApp2 ---");
        googleStock.deregister(mobileApp2);

        // Change price → only 2 get notified now
        googleStock.setPrice(2750.75);
    }
}