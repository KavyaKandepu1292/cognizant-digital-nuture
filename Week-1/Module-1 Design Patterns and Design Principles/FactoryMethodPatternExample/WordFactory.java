// WordFactory.java
// This factory ONLY creates Word documents

public class WordFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        System.out.println("WordFactory: Creating Word Document...");
        return new WordDocument();
    }
}