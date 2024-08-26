package com.rasmoo.codeinbook.common.dto.response;

import com.rasmoo.codeinbook.common.dto.CategoryDTO;
import com.rasmoo.codeinbook.common.enums.CategoryType;

import java.util.List;

public record CategoryResponseDTO(
        String id,
        String name,

        CategoryType categoryType,

        List<CategoryDTO> secondariesCategory

) {
}
