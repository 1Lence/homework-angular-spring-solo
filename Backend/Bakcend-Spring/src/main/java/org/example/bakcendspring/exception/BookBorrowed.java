package org.example.bakcendspring.exception;

public class BookBorrowed extends RuntimeException {
    public BookBorrowed(String message) {
        super(message);
    }
}
