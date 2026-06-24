public class Fan {

    private String location;

    public Fan(String location) {
        this.location = location;
    }

    public void turnOn() {
        System.out.println(location + " Fan is ON");
    }

    public void turnOff() {
        System.out.println(location + " Fan is OFF");
    }
}