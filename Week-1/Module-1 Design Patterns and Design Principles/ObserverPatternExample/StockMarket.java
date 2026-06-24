import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock {

    private List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double price;

    public StockMarket(String stockName) {
        this.stockName = stockName;
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
        System.out.println(" Observer registered: " 
                            + observer.getClass().getSimpleName());
    }

    @Override
    public void deregister(Observer observer) {
        observers.remove(observer);
        System.out.println(" Observer removed: " 
                            + observer.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, price);
        }
    }

    // When price changes → notify all observers automatically
    public void setPrice(double price) {
        System.out.println("\n " + stockName 
                            + " price changed to: $" + price);
        this.price = price;
        notifyObservers();  // tell everyone!
    }
}