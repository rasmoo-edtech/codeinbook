package com.rasmoo.codeinbook.common.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthorDTO(
        String id,
        @NotBlank(message = "must not be blank") String name,
        String resume

) {
}
