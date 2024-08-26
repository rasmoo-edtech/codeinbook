package com.rasmoo.codeinbook.common.dto;

import com.rasmoo.codeinbook.common.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
        String id,

        @NotBlank(message = "must be not blank")
        String name,

        @NotBlank(message = "must be not blank")
        CategoryType categoryType,
        String categoryPrimaryId
) {}
