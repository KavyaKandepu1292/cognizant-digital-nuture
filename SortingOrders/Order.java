public class Order {
    private int    orderId;
    private String customerName;
    private double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId      = orderId;
        this.customerName = customerName;
        this.totalPrice   = totalPrice;
    }

    public double getTotalPrice()   { return totalPrice;   }
    public int    getOrderId()      { return orderId;      }
    public String getCustomerName() { return customerName; }

    @Override
    public String toString() {
        return String.format("Order[ID=%-3d, Customer=%-10s, Price=$%.2f]",
                              orderId, customerName, totalPrice);
    }
}