package com.rasmoo.codeinbook.application.adapter.out.repository;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.PageDTO;
import com.rasmoo.codeinbook.common.exception.NotFoundException;
import com.rasmoo.codeinbook.domain.port.out.BookRepositoryPort;
import com.rasmoo.codeinbook.infrastructure.model.Book;
import com.rasmoo.codeinbook.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookRepositoryAdapter implements BookRepositoryPort {

    private BookRepository bookRepository;

    public BookRepositoryAdapter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO create(BookDTO dto) {
        Book book = new Book();
        book.with(dto);
        return bookRepository.save(book).toBookDTO();
    }

    @Override
    public void update(String id, BookDTO dto) {
        Book book = getBook(id);
        book.with(dto);
        bookRepository.save(book);
    }

    @Override
    public BookDTO findById(String id) {
        return getBook(id).toBookDTO();
    }

    @Override
    public void deleteById(String id) {
        getBook(id);
        bookRepository.deleteById(id);
    }

    @Override
    public PageDTO<BookDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Book> bookPage = bookRepository.findAll(pageRequest);
        List<BookDTO> bookList =
                bookPage.stream()
                        .map(Book::toBookDTO)
                        .toList();
        return PageDTO.<BookDTO>builder()
                .content(bookList)
                .page(page)
                .size(size)
                .totalElements(bookPage.getTotalElements())
                .totalPages(bookPage.getTotalPages())
                .build();
    }

    private Book getBook(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new NotFoundException("Book not found");
        }
        return bookOptional.get();
    }
}
