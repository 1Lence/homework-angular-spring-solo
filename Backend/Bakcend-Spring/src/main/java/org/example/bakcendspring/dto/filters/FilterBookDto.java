package org.example.bakcendspring.dto.filters;

import lombok.Builder;
import org.example.bakcendspring.entity.domain.Status;

import java.time.LocalDate;

@Builder
public record FilterBookDto(String author,
                            String title,
                            Status status,
                            LocalDate publishedDate) {
}
