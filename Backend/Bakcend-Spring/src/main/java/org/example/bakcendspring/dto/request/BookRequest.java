package org.example.bakcendspring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.example.bakcendspring.entity.domain.Status;
import org.hibernate.validator.constraints.ISBN;

import java.time.LocalDate;

@Builder
public record BookRequest (String title,
                           @NotBlank String author,
                           @ISBN @NotBlank String isbn,
                           @NotNull LocalDate publishedDate,
                           @NotNull Status status) {}