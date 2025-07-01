package org.example.bakcendspring.services.mappers;

import org.example.bakcendspring.dto.request.BookRequest;
import org.example.bakcendspring.dto.response.BookResponse;
import org.example.bakcendspring.entity.BookEntity;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public BookEntity toBookEntity(BookRequest book) {
        return BookEntity
                .builder()
                .title(book.title())
                .author(book.author())
                .isbn(book.isbn())
                .publishedDate(book.publishedDate())
                .status(book.status())
                .build();
    }

    public BookResponse toBookDtoResponse(BookEntity book) {
        return BookResponse
                .builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publishedDate(book.getPublishedDate())
                .status(book.getStatus())
                .build();
    }
}