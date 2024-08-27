package com.rasmoo.codeinbook.domain.port.out;

import com.rasmoo.codeinbook.common.dto.CategoryDTO;
import com.rasmoo.codeinbook.common.dto.response.CategoryResponseDTO;
import com.rasmoo.codeinbook.common.enums.CategoryType;

import java.util.List;

public interface CategoryRepositoryPort {

    CategoryDTO create(CategoryDTO dto);

    List<CategoryResponseDTO> findAll();

    CategoryDTO findById(String id);

    List<CategoryDTO> findAllByCategoryType(CategoryType categoryType);

    List<CategoryDTO> findAllByCategoryId(String primaryCategoryId);

    void deleteById(String id);

}
