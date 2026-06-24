// ExcelFactory.java
// This factory ONLY creates Excel documents

public class ExcelFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        System.out.println("ExcelFactory: Creating Excel Document...");
        return new ExcelDocument();
    }
}