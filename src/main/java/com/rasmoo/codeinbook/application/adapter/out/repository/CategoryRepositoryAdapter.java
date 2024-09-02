package com.rasmoo.codeinbook.application.adapter.out.repository;

import com.rasmoo.codeinbook.common.dto.CategoryDTO;
import com.rasmoo.codeinbook.common.dto.response.CategoryResponseDTO;
import com.rasmoo.codeinbook.common.enums.CategoryType;
import com.rasmoo.codeinbook.common.exception.NotFoundException;
import com.rasmoo.codeinbook.domain.port.out.repository.CategoryRepositoryPort;
import com.rasmoo.codeinbook.infrastructure.model.Category;
import com.rasmoo.codeinbook.infrastructure.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.rasmoo.codeinbook.common.enums.CategoryType.PRIMARY;
import static java.util.Objects.nonNull;

@Component
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {

    private final CategoryRepository categoryRepository;

    public CategoryRepositoryAdapter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        Category category = new Category();
        category.with(dto, nonNull(dto.primaryCategoryId())
                ? getCategory(dto.primaryCategoryId())
                : null);
        return categoryRepository.save(category).toCategoryDTO();
    }

    @Override
    public List<CategoryResponseDTO> findAll() {
        return findAllByCategoryType(PRIMARY)
                .stream()
                .map(this::convertToCategoryResponseDTO)
                .toList();
    }

    @Override
    public CategoryDTO findById(String id) {
        return getCategory(id).toCategoryDTO();
    }

    @Override
    public List<CategoryDTO> findAllByCategoryType(CategoryType categoryType) {
        return categoryRepository.findAllByCategoryType(categoryType)
                .stream().map(Category::toCategoryDTO).toList();
    }

    @Override
    public List<CategoryDTO> findAllByCategoryId(String primaryCategoryId) {
        return categoryRepository.findAllByPrimaryCategoryId(primaryCategoryId)
                .stream().map(Category::toCategoryDTO).toList();
    }

    @Override
    public void deleteById(String id) {
        getCategory(id);
        categoryRepository.deleteById(id);
    }

    private Category getCategory(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        return category.get();
    }

    private CategoryResponseDTO convertToCategoryResponseDTO(CategoryDTO primaryCategory) {
        return new CategoryResponseDTO(
                primaryCategory.id(),
                primaryCategory.name(),
                primaryCategory.categoryType(),
                findAllByCategoryId(primaryCategory.id())
                        .stream()
                        .map(secondCategory -> new CategoryDTO(
                                secondCategory.id(),
                                secondCategory.name(),
                                secondCategory.categoryType(),
                                secondCategory.primaryCategoryId()
                        )).toList()
        );
    }
}
