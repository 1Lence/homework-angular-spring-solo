package org.example.bakcendspring.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(Long id,
                           String userName,
                           String fullName,
                           String email) {
}
