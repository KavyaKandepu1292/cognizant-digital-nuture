public class CommandTest {

    public static void main(String[] args) {

        System.out.println("=== Command Pattern Demo ===\n");

        // Create receivers
        Light livingRoomLight = new Light("Living Room");
        Light bedroomLight    = new Light("Bedroom");
        Fan   ceilingFan      = new Fan("Ceiling");

        // Create commands
        Command lightOn     = new LightOnCommand(livingRoomLight);
        Command lightOff    = new LightOffCommand(livingRoomLight);
        Command bedLightOn  = new LightOnCommand(bedroomLight);
        Command fanOn       = new FanOnCommand(ceilingFan);
        Command fanOff      = new FanOffCommand(ceilingFan);

        // Create invoker (remote control)
        RemoteControl remote = new RemoteControl();

        // Test Light ON
        System.out.println("--- Pressing Light ON ---");
        remote.setCommand(lightOn);
        remote.pressButton();

        System.out.println();

        // Test Light OFF
        System.out.println("--- Pressing Light OFF ---");
        remote.setCommand(lightOff);
        remote.pressButton();

        System.out.println();

        // Test Undo
        System.out.println("--- Pressing UNDO ---");
        remote.pressUndo();  // undoes lightOff → turns ON again

        System.out.println();

        // Test Bedroom light
        System.out.println("--- Bedroom Light ON ---");
        remote.setCommand(bedLightOn);
        remote.pressButton();

        System.out.println();

        // Test Fan ON
        System.out.println("--- Fan ON ---");
        remote.setCommand(fanOn);
        remote.pressButton();

        System.out.println();

        // Test Fan OFF
        System.out.println("--- Fan OFF ---");
        remote.setCommand(fanOff);
        remote.pressButton();

        System.out.println();

        // Test Undo fan off
        System.out.println("--- Undo Fan OFF ---");
        remote.pressUndo();  // fan turns ON again
    }
}