package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("=== Library Management App Started ===\n");

        // Load Spring context from XML
        ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

        // Exercise 1: Basic Spring
        System.out.println("\n--- Exercise 1: Basic Spring ---");
        BookService service = (BookService) context.getBean("bookService");
        service.addBook("Design Patterns");
        service.getBook(1);
        service.removeBook(1);

        // Exercise 2 & 7: Dependency Injection
        System.out.println("\n--- Exercise 2 & 7: Dependency Injection ---");

        // Setter Injection
        System.out.println("\n[Setter Injection]");
        BookService setterService =
            (BookService) context.getBean("bookServiceSetter");
        setterService.addBook("Clean Code");

        // Constructor Injection
        System.out.println("\n[Constructor Injection]");
        BookService constructorService =
            (BookService) context.getBean("bookServiceConstructor");
        constructorService.addBook("Spring in Action");

        System.out.println("\n=== App Finished ===");

        ((ClassPathXmlApplicationContext) context).close();
    }
}