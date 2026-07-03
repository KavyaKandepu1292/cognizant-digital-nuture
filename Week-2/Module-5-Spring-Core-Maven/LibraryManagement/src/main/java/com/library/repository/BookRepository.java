package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    public void save(String bookName) {
        System.out.println(" BookRepository: Saving book → " + bookName);
    }

    public String findById(int id) {
        System.out.println(" BookRepository: Finding book with ID → " + id);
        return "Spring Boot in Action";
    }

    public void delete(int id) {
        System.out.println(" BookRepository: Deleting book with ID → " + id);
    }
}