package org.example.bakcendspring.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.bakcendspring.dto.request.BookRequest;
import org.example.bakcendspring.dto.filters.FilterBookDto;
import org.example.bakcendspring.dto.response.BookResponse;
import org.example.bakcendspring.entity.BookEntity;
import org.example.bakcendspring.entity.domain.Status;
import org.example.bakcendspring.repositories.BookRepository;
import org.example.bakcendspring.services.base.CrudService;
import org.example.bakcendspring.services.mappers.BookMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements CrudService<Long, BookRequest, BookResponse, FilterBookDto> {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookEntity findById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Books not found with id: " + id)
        );
    }

    @Transactional(readOnly = true)
    public List<BookResponse> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookDtoResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getById(Long id) {
        return bookMapper.toBookDtoResponse(findById(id));
    }

    @Transactional(readOnly = true)
    public List<BookResponse> getAllFiltered(FilterBookDto filter) {
        return bookRepository.findByFilters(filter.title(), filter.author(), filter.status(), filter.publishedDate())
                .stream()
                .map(bookMapper::toBookDtoResponse)
                .toList();
    }

    @Override
    @Transactional
    public void update(BookRequest bookRequest, Long id) {
        var entity = findById(id);

        entity.setAuthor(bookRequest.author());
        entity.setTitle(bookRequest.title());
        entity.setStatus(bookRequest.status());
        entity.setPublishedDate(bookRequest.publishedDate());
    }

    @Override
    @Transactional
    public void create(BookRequest bookRequest) {
        bookRepository.save(bookMapper.toBookEntity(bookRequest));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void changeStatus(BookEntity bookEntity, Status status) {
        bookEntity.setStatus(status);
        bookRepository.save(bookEntity);
    }
}