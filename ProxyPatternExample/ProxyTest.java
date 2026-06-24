public class ProxyTest {

    public static void main(String[] args) {

        System.out.println("=== Proxy Pattern Demo ===\n");

        // ProxyImage created — NO server call yet!
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        System.out.println("--- Display image1 (1st time) ---");
        image1.display();  // loads from server

        System.out.println();

        System.out.println("--- Display image1 (2nd time) ---");
        image1.display();  // uses cache — no server call!

        System.out.println();

        System.out.println("--- Display image2 (1st time) ---");
        image2.display();  // loads from server

        System.out.println();

        System.out.println("--- Display image2 (2nd time) ---");
        image2.display();  // uses cache!
    }
}