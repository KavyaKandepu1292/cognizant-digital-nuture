// FactoryMethodTest.java
// Test class - demonstrates Factory Method Pattern

public class FactoryMethodTest {

    public static void main(String[] args) {

        System.out.println("=== Factory Method Pattern Demo ===\n");

        // Create Word document using WordFactory
        System.out.println("--- Word Document ---");
        DocumentFactory wordFactory = new WordFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        wordDoc.close();

        System.out.println();

        // Create PDF document using PdfFactory
        System.out.println("--- PDF Document ---");
        DocumentFactory pdfFactory = new PdfFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        pdfDoc.close();

        System.out.println();

        // Create Excel document using ExcelFactory
        System.out.println("--- Excel Document ---");
        DocumentFactory excelFactory = new ExcelFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
        excelDoc.close();

        System.out.println();

        // Using openDocument() helper method
        System.out.println("--- Using openDocument() method ---");
        wordFactory.openDocument();
        pdfFactory.openDocument();
        excelFactory.openDocument();
    }
}