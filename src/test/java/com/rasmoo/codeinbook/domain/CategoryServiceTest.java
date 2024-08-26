package com.rasmoo.codeinbook.domain;

import com.rasmoo.codeinbook.common.dto.CategoryDTO;
import com.rasmoo.codeinbook.common.enums.CategoryType;
import com.rasmoo.codeinbook.domain.port.out.CategoryRepositoryPort;
import com.rasmoo.codeinbook.domain.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepositoryPort categoryRepositoryPort;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldCreateNewCategory() {
        CategoryDTO categoryDTO = getCategory(null);
        CategoryDTO categorySaved = getCategory("123");
        when(categoryRepositoryPort.create(categoryDTO)).thenReturn(categorySaved);
        assertEquals(categorySaved, categoryService.create(categoryDTO));
        verify(categoryRepositoryPort, times(1)).create(categoryDTO);
    }

    @Test
    void shouldNotCreateNewCategoryWhenThereAreMoreThenFiveAlreadyCreated() {
        CategoryDTO categoryDTO = getCategory(null);
        CategoryDTO categorySaved = getCategory("123");
        when(categoryRepositoryPort.create(categoryDTO)).thenReturn(categorySaved);
        assertEquals(categorySaved, categoryService.create(categoryDTO));
        verify(categoryRepositoryPort, times(1)).create(categoryDTO);
    }

    private static CategoryDTO getCategory(String number) {
        return new CategoryDTO(number, "Front-end", CategoryType.PRIMARY, null);
    }
}
