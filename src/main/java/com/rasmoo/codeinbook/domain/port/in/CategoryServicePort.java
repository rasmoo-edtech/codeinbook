package com.rasmoo.codeinbook.domain.port.in;

import com.rasmoo.codeinbook.common.dto.CategoryDTO;
import com.rasmoo.codeinbook.common.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryServicePort {

    CategoryDTO create(CategoryDTO dto);

    List<CategoryResponseDTO> findAll();

    void update(String id);

    void deleteById(String id);

}
