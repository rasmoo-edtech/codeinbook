package com.rasmoo.codeinbook.application.adapter.out;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.PageDTO;
import com.rasmoo.codeinbook.common.exception.NotFoundException;
import com.rasmoo.codeinbook.domain.port.out.BookRepositoryPort;
import com.rasmoo.codeinbook.infrastructure.model.Book;
import com.rasmoo.codeinbook.infrastructure.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookRepositoryAdapter implements BookRepositoryPort {

    private BookRepository bookRepository;
    @Override
    public BookDTO create(BookDTO dto) {
        Book book = new Book();
        book.with(dto);
        return bookRepository.save(book).toBookDTO();
    }

    @Override
    public void update(String id, BookDTO dto) {

    }

    @Override
    public BookDTO findById(String id) {
        return getBook(id).toBookDTO();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public PageDTO<BookDTO> findAll(int page, int size) {
        return null;
    }

    private Book getBook(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new NotFoundException("Book not found");
        }
        return bookOptional.get();
    }
}
