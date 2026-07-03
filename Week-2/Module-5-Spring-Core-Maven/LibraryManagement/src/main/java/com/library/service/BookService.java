package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    // Default constructor
    public BookService() {
        System.out.println(" BookService: Created!");
    }

    // Constructor injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println(" BookService: Constructor Injection done!");
    }

    // Setter injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println(" BookService: Setter Injection done!");
    }

    public void addBook(String bookName) {
        System.out.println(" BookService: Adding book → " + bookName);
        bookRepository.save(bookName);
    }

    public String getBook(int id) {
        System.out.println(" BookService: Getting book ID → " + id);
        return bookRepository.findById(id);
    }

    public void removeBook(int id) {
        System.out.println(" BookService: Removing book ID → " + id);
        bookRepository.delete(id);
    }
}