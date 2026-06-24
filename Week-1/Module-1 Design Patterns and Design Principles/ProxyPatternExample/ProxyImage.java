public class ProxyImage implements Image {

    private String filename;
    private RealImage realImage;  // null until first display()

    public ProxyImage(String filename) {
        this.filename = filename;
        // RealImage NOT created here — lazy initialization!
    }

    @Override
    public void display() {
        // Create RealImage only when display() is called first time
        if (realImage == null) {
            System.out.println("ProxyImage: First time — creating RealImage...");
            realImage = new RealImage(filename);  // loads from server
        } else {
            System.out.println("ProxyImage: Cached — no server call needed!");
        }
        realImage.display();
    }
}