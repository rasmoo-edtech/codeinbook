package com.rasmoo.codeinbook.domain.service;

import com.rasmoo.codeinbook.common.dto.CategoryBookEventDTO;
import com.rasmoo.codeinbook.common.dto.CategoryDTO;
import com.rasmoo.codeinbook.common.dto.response.CategoryResponseDTO;
import com.rasmoo.codeinbook.common.exception.BadRequestException;
import com.rasmoo.codeinbook.common.exception.BusinessException;
import com.rasmoo.codeinbook.domain.port.in.CategoryServicePort;
import com.rasmoo.codeinbook.domain.port.out.producer.EventProducerPort;
import com.rasmoo.codeinbook.domain.port.out.repository.CategoryRepositoryPort;

import java.util.List;

import static com.rasmoo.codeinbook.common.enums.CategoryType.PRIMARY;
import static com.rasmoo.codeinbook.common.enums.CategoryType.SECONDARY;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public class CategoryService implements CategoryServicePort {
    private final CategoryRepositoryPort categoryRepositoryPort;

    private final EventProducerPort eventProducerPort;

    public CategoryService(CategoryRepositoryPort categoryRepositoryPort,
                           EventProducerPort eventProducerPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
        this.eventProducerPort = eventProducerPort;
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        checkCategoryBond(dto);
        checkCategoryLimit(dto);
        return categoryRepositoryPort.create(dto);
    }

    @Override
    public List<CategoryResponseDTO> findAll() {
        return categoryRepositoryPort.findAll();
    }

    @Override
    public void deleteById(String id) {
        CategoryDTO categoryDTO = categoryRepositoryPort.findById(id);
        String categoryTarget = null;
        if (PRIMARY.equals(categoryDTO.categoryType())
                && !categoryRepositoryPort.findAllByCategoryId(id).isEmpty()) {
            throw new BusinessException("Primary category can not be deleted while a secondary one is linked");
        } else if (SECONDARY.equals(categoryDTO.categoryType())) {
            categoryTarget = categoryDTO.primaryCategoryId();
        }
        categoryRepositoryPort.deleteById(id);
        eventProducerPort.updateCategoryBook(new CategoryBookEventDTO(id, categoryTarget));
    }

    private void checkCategoryBond(CategoryDTO dto) {
        if (SECONDARY.equals(dto.categoryType())
                && ((isNull(dto.primaryCategoryId()) || dto.primaryCategoryId().isBlank())
                || SECONDARY.equals(categoryRepositoryPort.findById(dto.primaryCategoryId()).categoryType()))) {
            throw new BadRequestException("Secondary category must have a primary category id");
        } else if (PRIMARY.equals(dto.categoryType())
                && nonNull(dto.primaryCategoryId())) {
            throw new BadRequestException("Primary category can not be linked to another category");
        }
    }

    private void checkCategoryLimit(CategoryDTO dto) {
        if (PRIMARY.equals(dto.categoryType())
                && categoryRepositoryPort.findAllByCategoryType(PRIMARY).size() > 4) {
            throw new BusinessException("Primary Category reached the limit");
        } else if (SECONDARY.equals(dto.categoryType())
                && categoryRepositoryPort.findAllByCategoryId(dto.primaryCategoryId()).size() > 6) {
            throw new BusinessException("Primary Category reached the limit of Secondaries Categories");
        }
    }
}
