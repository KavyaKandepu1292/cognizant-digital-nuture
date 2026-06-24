// PdfFactory.java
// This factory ONLY creates PDF documents

public class PdfFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        System.out.println("PdfFactory: Creating PDF Document...");
        return new PdfDocument();
    }
}