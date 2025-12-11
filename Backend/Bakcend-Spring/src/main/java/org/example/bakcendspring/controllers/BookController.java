package org.example.bakcendspring.controllers;

import lombok.RequiredArgsConstructor;
import org.example.bakcendspring.dto.request.BookRequest;
import org.example.bakcendspring.dto.filters.FilterBookDto;
import org.example.bakcendspring.dto.response.BookResponse;
import org.example.bakcendspring.services.BookService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/filtered")
    public List<BookResponse> getAllBooks(@RequestBody FilterBookDto filter) {
        return bookService.getAllFiltered(filter);
    }

    @GetMapping
    public List<BookResponse> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody @Validated BookRequest bookRequest) {
        bookService.create(bookRequest);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody @Validated BookRequest bookRequest, @PathVariable Long id) {
        bookService.update(bookRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}