package org.example.bakcendspring.dto.response;

import lombok.Builder;
import org.example.bakcendspring.entity.domain.Status;

import java.time.LocalDate;

@Builder
public record BookResponse(Long id,
                           String title,
                           String author,
                           String isbn,
                           LocalDate publishedDate,
                           Status status) {
}