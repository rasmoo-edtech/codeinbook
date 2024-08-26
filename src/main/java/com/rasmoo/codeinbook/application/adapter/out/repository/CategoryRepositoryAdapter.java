package com.rasmoo.codeinbook.application.adapter.out.repository;

import com.rasmoo.codeinbook.common.dto.CategoryDTO;
import com.rasmoo.codeinbook.common.dto.response.CategoryResponseDTO;
import com.rasmoo.codeinbook.common.enums.CategoryType;
import com.rasmoo.codeinbook.domain.port.out.CategoryRepositoryPort;
import com.rasmoo.codeinbook.infrastructure.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {

    private CategoryRepository categoryRepository;

    public CategoryRepositoryAdapter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        return null;
    }

    @Override
    public List<CategoryResponseDTO> findAll() {
        return null;
    }

    @Override
    public CategoryDTO findById(String id) {
        return null;
    }

    @Override
    public List<CategoryDTO> findAllByCategoryType(CategoryType categoryType) {
        return null;
    }

    @Override
    public List<CategoryDTO> findAllByCategoryId(String categoryPrimaryId) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void update(String id) {

    }
}
