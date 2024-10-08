package com.rasmoo.codeinbook.domain;


import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.response.PageResponseDTO;
import com.rasmoo.codeinbook.domain.port.out.repository.BookRepositoryPort;
import com.rasmoo.codeinbook.domain.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepositoryPort bookRepositoryPort;
    @InjectMocks
    private BookService bookService;


    @Test
    void shouldCreateNewBook() {
        BookDTO bookDTO = new BookDTO("", "Partindo do zero com java", "aprenda a programar agora", "", BigDecimal.valueOf(29.90), null, null);
        BookDTO bookSaved = getBookSaved();
        when(bookRepositoryPort.create(bookDTO)).thenReturn(bookSaved);
        assertEquals(bookSaved, bookService.create(bookDTO));
        verify(bookRepositoryPort, times(1)).create(bookDTO);
    }

    @Test
    void shouldUpdateBook() {
        BookDTO bookSaved = getBookSaved();
        bookService.update("123",bookSaved);
        verify(bookRepositoryPort, times(1)).update("123", bookSaved, null);

    }

    @Test
    void shouldGetBookById() {
        BookDTO bookSaved = getBookSaved();
        when(bookRepositoryPort.findById("123")).thenReturn(bookSaved);
        assertEquals(bookSaved, bookService.findById("123"));
    }

    @Test
    void shouldDeleteBook() {
        bookService.deleteById("123");
        verify(bookRepositoryPort, times(1)).deleteById("123");
    }

    @Test
    void shouldGetAllBooksPaged() {
        List<BookDTO> bookList = List.of(getBookSaved());

        PageResponseDTO<BookDTO> page = PageResponseDTO.<BookDTO>builder()
                        .content(bookList)
                .page(0)
                .size(10)
                .totalElements(1000)
                .totalPages(100)
                .build();

        when(bookRepositoryPort.findAll(0,10, null)).thenReturn(page);
        assertEquals(page, bookService.findAll(0,10,null));
    }

    private static BookDTO getBookSaved() {
        return new BookDTO("123", "Partindo do zero com java", "aprenda a programar agora", "", BigDecimal.valueOf(29.90), null, null);
    }



}
