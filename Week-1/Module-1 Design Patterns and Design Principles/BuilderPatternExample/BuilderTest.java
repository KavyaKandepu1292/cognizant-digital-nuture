// BuilderTest.java
// Test class — creates 3 different Computer configurations

public class BuilderTest {

    public static void main(String[] args) {

        System.out.println("=== Builder Pattern Demo ===");

        // Computer 1 — Basic configuration (only required parts)
        Computer basicPC = new Computer.Builder("Intel i3", "8GB", "256GB SSD")
                .build();

        System.out.println("\n Basic PC:");
        System.out.println(basicPC);

        // Computer 2 — Gaming configuration (all optional parts added)
        Computer gamingPC = new Computer.Builder("Intel i9", "32GB", "1TB SSD")
                .setGPU("NVIDIA RTX 4090")
                .setOS("Windows 11")
                .setBluetooth(true)
                .setWifi(true)
                .build();

        System.out.println("\n Gaming PC:");
        System.out.println(gamingPC);

        // Computer 3 — Office configuration (some optional parts)
        Computer officePC = new Computer.Builder("Intel i5", "16GB", "512GB SSD")
                .setOS("Windows 10")
                .setWifi(true)
                .setBluetooth(false)
                .build();

        System.out.println("\n Office PC:");
        System.out.println(officePC);
    }
}