package com.rasmoo.codeinbook.domain;

import com.rasmoo.codeinbook.common.dto.CategoryDTO;
import com.rasmoo.codeinbook.common.dto.response.CategoryResponseDTO;
import com.rasmoo.codeinbook.common.enums.CategoryType;
import com.rasmoo.codeinbook.common.exception.BadRequestException;
import com.rasmoo.codeinbook.common.exception.BusinessException;
import com.rasmoo.codeinbook.domain.port.out.repository.CategoryRepositoryPort;
import com.rasmoo.codeinbook.domain.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static com.rasmoo.codeinbook.common.enums.CategoryType.PRIMARY;
import static com.rasmoo.codeinbook.common.enums.CategoryType.SECONDARY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepositoryPort categoryRepositoryPort;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldCreateNewCategory() {
        CategoryDTO categoryDTO = getPrimaryCategory(null);
        CategoryDTO categorySaved = getPrimaryCategory("123");
        when(categoryRepositoryPort.create(categoryDTO)).thenReturn(categorySaved);
        when(categoryRepositoryPort.findAllByCategoryType(PRIMARY)).thenReturn(List.of(categorySaved));
        assertEquals(categorySaved, categoryService.create(categoryDTO));
        verify(categoryRepositoryPort, times(1)).create(categoryDTO);
        verify(categoryRepositoryPort, times(0)).findById(any());
        verify(categoryRepositoryPort, times(1)).findAllByCategoryType(PRIMARY);
        verify(categoryRepositoryPort, times(0)).findAllByCategoryId(any());
    }

    @Test
    void shouldNotCreateNewCategoryWhenThereAreMoreThenFiveAlreadyCreated() {
        CategoryDTO categoryDTO = getPrimaryCategory(null);
        CategoryDTO categorySaved = getPrimaryCategory("123");
        when(categoryRepositoryPort.findAllByCategoryType(PRIMARY))
                .thenReturn(List.of(categorySaved, categorySaved, categorySaved, categorySaved, categorySaved, categorySaved));

        assertThrows(BusinessException.class,
                () -> categoryService.create(categoryDTO));
        verify(categoryRepositoryPort, times(0)).create(categoryDTO);
        verify(categoryRepositoryPort, times(0)).findById(any());
        verify(categoryRepositoryPort, times(1)).findAllByCategoryType(PRIMARY);
        verify(categoryRepositoryPort, times(0)).findAllByCategoryId(any());
    }

    @Test
    void shouldNotCreateWhenItIsSecondaryCategoryAndPrimaryNotInformed() {
        CategoryDTO categoryDTO = new CategoryDTO("234", "Angular", SECONDARY, null);
        assertThrows(BadRequestException.class,
                () -> categoryService.create(categoryDTO));
        verify(categoryRepositoryPort, times(0)).create(categoryDTO);
        verify(categoryRepositoryPort, times(0)).findById(any());
        verify(categoryRepositoryPort, times(0)).findAllByCategoryType(PRIMARY);
        verify(categoryRepositoryPort, times(0)).findAllByCategoryId("123");
    }

    @Test
    void shouldNotCreateWhenItIsSecondaryCategoryAndPrimaryCategoryIsWrong() {
        CategoryDTO categoryDTO = new CategoryDTO("234", "Angular", SECONDARY, "123");
        when(categoryRepositoryPort.findById(categoryDTO.primaryCategoryId()))
                .thenReturn(getSecondaryCategory("123"));
        assertThrows(BadRequestException.class,
                () -> categoryService.create(categoryDTO));
        verify(categoryRepositoryPort, times(0)).create(categoryDTO);
        verify(categoryRepositoryPort, times(1)).findById(any());
        verify(categoryRepositoryPort, times(0)).findAllByCategoryType(PRIMARY);
        verify(categoryRepositoryPort, times(0)).findAllByCategoryId("123");
    }

    @Test
    void shouldNotCreateWhenItIsPrimaryCategoryAndAnotherOneIsInformed() {
        CategoryDTO categoryDTO = new CategoryDTO("234", "Angular", PRIMARY, "123");
        assertThrows(BadRequestException.class,
                () -> categoryService.create(categoryDTO));
        verify(categoryRepositoryPort, times(0)).create(categoryDTO);
        verify(categoryRepositoryPort, times(0)).findById(any());
        verify(categoryRepositoryPort, times(0)).findAllByCategoryType(PRIMARY);
        verify(categoryRepositoryPort, times(0)).findAllByCategoryId("123");
    }

    @Test
    void shouldNotCreateNewSubCategoryWhenThereAreMoreThenSevenAlreadyCreated() {
        CategoryDTO categoryDTO = getSecondaryCategory(null);
        CategoryDTO categorySaved = getSecondaryCategory("234");
        when(categoryRepositoryPort.findAllByCategoryId("123"))
                .thenReturn(List.of(categorySaved,categorySaved,categorySaved,
                        categorySaved,categorySaved,categorySaved,categorySaved));
        when(categoryRepositoryPort.findById(categoryDTO.primaryCategoryId()))
                .thenReturn(getPrimaryCategory("123"));
        assertThrows(BusinessException.class,
                () -> categoryService.create(categoryDTO));
        verify(categoryRepositoryPort, times(0)).create(categoryDTO);
        verify(categoryRepositoryPort, times(1)).findById(any());
        verify(categoryRepositoryPort, times(0)).findAllByCategoryType(PRIMARY);
        verify(categoryRepositoryPort, times(1)).findAllByCategoryId("123");
    }

    @Test
    void shouldFindAll() {
        when(categoryRepositoryPort.findAll()).thenReturn(List.of(new CategoryResponseDTO("123", null, null, null)));
        assertNotEquals(Collections.emptyList(), categoryService.findAll());
    }

    @Test
    void shouldDeleteById() {
        when(categoryRepositoryPort.findById("123")).thenReturn(new CategoryDTO("123", "name", CategoryType.SECONDARY, "234"));
        categoryService.deleteById("123");
        verify(categoryRepositoryPort, times(1)).deleteById("123");
        verify(categoryRepositoryPort, times(0)).findAllByCategoryId("123");
    }

    @Test
    void shouldNotDeleteByIdWhenPrimaryCategoryIsLinkedToAnotherSecondCategory() {
        when(categoryRepositoryPort.findById("123")).thenReturn(new CategoryDTO("123", "name", PRIMARY, null));
        when(categoryRepositoryPort.findAllByCategoryId("123")).thenReturn(List.of(new CategoryDTO("345", "name", CategoryType.SECONDARY, "123")));
        assertThrows(BusinessException.class,
                () -> categoryService.deleteById("123"));
        verify(categoryRepositoryPort, times(0)).deleteById("123");
        verify(categoryRepositoryPort, times(1)).findAllByCategoryId("123");
    }

    private static CategoryDTO getPrimaryCategory(String number) {
        return new CategoryDTO(number, "Front-end", PRIMARY, null);
    }

    private static CategoryDTO getSecondaryCategory(String number) {
        return new CategoryDTO(number, "Angular", SECONDARY, "123");
    }
}
