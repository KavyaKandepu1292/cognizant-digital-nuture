// ExcelDocument.java
// Concrete class - actual Excel document

public class ExcelDocument implements Document {

    @Override
    public void open() {
        System.out.println("Opening Excel Document (.xlsx)");
    }

    @Override
    public void close() {
        System.out.println("Closing Excel Document (.xlsx)");
    }
}