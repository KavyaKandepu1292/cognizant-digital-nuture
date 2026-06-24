// DecoratorTest.java
// Test class — shows different combinations of notifications

public class DecoratorTest {

    public static void main(String[] args) {

        System.out.println("=== Decorator Pattern Demo ===\n");

        // Case 1: Email only (base — no decoration)
        System.out.println("--- Case 1: Email Only ---");
        Notifier emailOnly = new EmailNotifier();
        emailOnly.send("Server is down!");

        System.out.println();

        // Case 2: Email + SMS
        System.out.println("--- Case 2: Email + SMS ---");
        Notifier emailAndSMS = new SMSNotifierDecorator(
                                    new EmailNotifier()
                               );
        emailAndSMS.send("Disk space low!");

        System.out.println();

        // Case 3: Email + SMS + Slack (all channels)
        System.out.println("--- Case 3: Email + SMS + Slack ---");
        Notifier allChannels = new SlackNotifierDecorator(
                                    new SMSNotifierDecorator(
                                        new EmailNotifier()
                                    )
                               );
        allChannels.send("Database connection failed!");

        System.out.println();

        // Case 4: Email + Slack only (skip SMS)
        System.out.println("--- Case 4: Email + Slack Only ---");
        Notifier emailAndSlack = new SlackNotifierDecorator(
                                      new EmailNotifier()
                                 );
        emailAndSlack.send("CPU usage is high!");
    }
}