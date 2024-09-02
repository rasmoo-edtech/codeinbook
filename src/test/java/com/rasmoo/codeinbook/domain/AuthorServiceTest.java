package com.rasmoo.codeinbook.domain;


import com.rasmoo.codeinbook.common.dto.AuthorDTO;
import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.response.PageResponseDTO;
import com.rasmoo.codeinbook.common.enums.SortDirection;
import com.rasmoo.codeinbook.common.exception.BusinessException;
import com.rasmoo.codeinbook.domain.port.out.repository.AuthorRepositoryPort;
import com.rasmoo.codeinbook.domain.port.out.repository.BookRepositoryPort;
import com.rasmoo.codeinbook.domain.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepositoryPort authorRepositoryPort;

    @Mock
    private BookRepositoryPort bookRepositoryPort;

    @InjectMocks
    private AuthorService authorService;


    @Test
    void shouldCreateNewAuthor() {
        AuthorDTO authorDTO = new AuthorDTO("", "Felipe Alves", "Engenheiro de software");
        AuthorDTO authorSaved = getAuthorSaved();
        when(authorRepositoryPort.create(authorDTO)).thenReturn(authorSaved);
        assertEquals(authorSaved, authorService.create(authorDTO));
        verify(authorRepositoryPort, times(1)).create(authorDTO);
    }

    @Test
    void shouldGetAuthorByName() {
        List<AuthorDTO> authorList = List.of(getAuthorSaved());

        PageResponseDTO<AuthorDTO> page = PageResponseDTO.<AuthorDTO>builder()
                .content(authorList)
                .page(0)
                .size(10)
                .totalElements(1000)
                .totalPages(100)
                .build();

        when(authorRepositoryPort.findAllByName("123", 0, 10, SortDirection.DESC)).thenReturn(page);
        assertEquals(page, authorService.findAllByName("123", 0, 10, SortDirection.DESC));
    }

    @Test
    void shouldDeleteBook() {
        when(bookRepositoryPort.findAllByAuthorId("123")).thenReturn(Collections.emptyList());
        authorService.deleteById("123");
        verify(authorRepositoryPort, times(1)).deleteById("123");
    }

    @Test
    void shouldNotDeleteBook() {
        when(bookRepositoryPort.findAllByAuthorId("123")).thenReturn(List.of(new BookDTO("123","title","",null,null,null,null)));
        assertThrows(BusinessException.class,
                () -> authorService.deleteById("123"));
        verify(authorRepositoryPort, times(0)).deleteById(any());
    }

    private static AuthorDTO getAuthorSaved() {
        return new AuthorDTO("123", "Felipe Alves", "Engenheiro de software");
    }


}
