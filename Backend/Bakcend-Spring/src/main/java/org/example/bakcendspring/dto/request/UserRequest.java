package org.example.bakcendspring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserRequest(@NotBlank String userName,
                          @NotBlank String fullName,
                          @Email @NotBlank String email) {
}
