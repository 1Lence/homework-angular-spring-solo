package org.example.bakcendspring.dto.filters;

import lombok.Builder;

@Builder
public record FilterUserDto(String userName,
                            String fullName) {
}
