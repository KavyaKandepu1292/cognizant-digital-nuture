// Computer.java
// This file has TWO classes inside:
// 1. Computer  — the product we want to build
// 2. Builder   — the builder (nested inside Computer)

public class Computer {

    
    // PART 1: Computer attributes (all private)
    
    private String cpu;       // must have
    private String ram;       // must have
    private String storage;   // must have
    private String gpu;       // optional
    private String os;        // optional
    private boolean bluetooth; // optional
    private boolean wifi;      // optional

    
    // PART 2: Private Constructor
    // Nobody can do: new Computer()
    // Only Builder can create Computer!
    
    private Computer(Builder builder) {
        this.cpu       = builder.cpu;
        this.ram       = builder.ram;
        this.storage   = builder.storage;
        this.gpu       = builder.gpu;
        this.os        = builder.os;
        this.bluetooth = builder.bluetooth;
        this.wifi      = builder.wifi;
    }

   
    // PART 3: toString to print Computer details
    
    @Override
    public String toString() {
        return "\n--- Computer Configuration ---" +
               "\nCPU       : " + cpu +
               "\nRAM       : " + ram +
               "\nStorage   : " + storage +
               "\nGPU       : " + (gpu != null ? gpu : "Not Added") +
               "\nOS        : " + (os != null ? os : "Not Added") +
               "\nBluetooth : " + bluetooth +
               "\nWiFi      : " + wifi +
               "\n------------------------------";
    }

    
    // PART 4: Static nested Builder class
    // This is the BUILDER — builds Computer step by step
    
    public static class Builder {

        // Builder has same fields as Computer
        private String cpu;
        private String ram;
        private String storage;
        private String gpu;
        private String os;
        private boolean bluetooth;
        private boolean wifi;

        // Builder constructor — only required fields here
        public Builder(String cpu, String ram, String storage) {
            this.cpu     = cpu;
            this.ram     = ram;
            this.storage = storage;
        }

        // Each method sets ONE field and returns "this"
        // returning "this" allows CHAINING: .setGPU().setOS().build()

        public Builder setGPU(String gpu) {
            this.gpu = gpu;
            return this;  // returns builder itself for chaining
        }

        public Builder setOS(String os) {
            this.os = os;
            return this;
        }

        public Builder setBluetooth(boolean bluetooth) {
            this.bluetooth = bluetooth;
            return this;
        }

        public Builder setWifi(boolean wifi) {
            this.wifi = wifi;
            return this;
        }

        // FINAL STEP: build() creates and returns the Computer!
        public Computer build() {
            return new Computer(this);
        }
    }
}