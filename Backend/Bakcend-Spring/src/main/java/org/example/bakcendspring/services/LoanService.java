package org.example.bakcendspring.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bakcendspring.dto.request.LoanRequest;
import org.example.bakcendspring.entity.LoanEntity;
import org.example.bakcendspring.entity.domain.Status;
import org.example.bakcendspring.exception.BookBorrowed;
import org.example.bakcendspring.repositories.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {
    private final UserService userService;
    private final BookService bookService;
    public final LoanRepository loanRepository;

    public LoanEntity findById(Long id) {
        return loanRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Books not found with id: " + id)
        );
    }

    @Transactional
    public void returnBook(Long id){
        var entity = findById(id);
        bookService.changeStatus(entity.getBook(), Status.AVAILABLE);

        entity.setReturnDate(LocalDate.now());
    }

    @Transactional
    public void createLoan(LoanRequest loanRequest){
        var book = bookService.findById(loanRequest.bookId());

        if(book.getStatus() == Status.BORROWED){
            throw new BookBorrowed("Book already borrowed");
        }

        var user  = userService.findById(loanRequest.userId());
        var loan = LoanEntity
                .builder()
                .book(book)
                .user(user)
                .loanDate(LocalDate.now())
                .returnDate(loanRequest.returnDate())
                .build();

        loanRepository.save(loan);

        bookService.changeStatus(book, Status.BORROWED);
    }
}