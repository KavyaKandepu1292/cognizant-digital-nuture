// DocumentFactory.java
// Abstract factory - every factory must implement createDocument()

public abstract class DocumentFactory {

    // This is the FACTORY METHOD
    // Every subclass must override this and return their document type
    public abstract Document createDocument();

    // Common method all factories can use
    public void openDocument() {
        Document doc = createDocument();  // factory creates it
        doc.open();                       // then opens it
    }
}