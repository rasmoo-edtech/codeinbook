package com.rasmoo.codeinbook.common.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record BookDTO(
        String id,
        @NotBlank(message = "must be not blank")
        String title,
        String subtitle,
        String description,
        BigDecimal price,
        String authorId,
        String categoryId


) {
}
