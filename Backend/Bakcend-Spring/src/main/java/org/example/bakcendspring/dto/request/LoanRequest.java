package org.example.bakcendspring.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record LoanRequest(@NotNull Long userId,
                          @NotNull Long bookId,
                          @NotNull LocalDate returnDate) {
}