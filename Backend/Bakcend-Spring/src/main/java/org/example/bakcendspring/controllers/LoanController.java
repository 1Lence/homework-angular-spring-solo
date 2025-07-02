package org.example.bakcendspring.controllers;

import lombok.RequiredArgsConstructor;
import org.example.bakcendspring.dto.request.LoanRequest;
import org.example.bakcendspring.services.LoanService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @PostMapping
    public void create(@Validated @RequestBody LoanRequest loanRequest) {
        loanService.createLoan(loanRequest);
    }

    @PutMapping("/return/{id}")
    public void returnBook(@PathVariable Long id) {
        loanService.returnBook(id);
    }
}
