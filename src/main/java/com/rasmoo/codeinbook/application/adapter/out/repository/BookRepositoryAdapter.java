package com.rasmoo.codeinbook.application.adapter.out.repository;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.response.PageResponseDTO;
import com.rasmoo.codeinbook.common.exception.NotFoundException;
import com.rasmoo.codeinbook.domain.port.out.repository.BookRepositoryPort;
import com.rasmoo.codeinbook.infrastructure.model.Book;
import com.rasmoo.codeinbook.infrastructure.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class BookRepositoryAdapter implements BookRepositoryPort {

    private final BookRepository bookRepository;

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
    public void update(String id, BookDTO dto, String categoryId) {
        Book book = getBook(id);
        if (nonNull(dto)) {
            book.with(dto);
        } else {
           book.withCategoryId(categoryId);
        }
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
    public PageResponseDTO<BookDTO> findAll(int page, int size, String categoryId) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Book> bookPage;
        if (isNull(categoryId) || categoryId.isBlank()) {
            bookPage = bookRepository.findAll(pageRequest);
        } else {
            bookPage = bookRepository.findAllByCategoryId(categoryId, pageRequest);
        }
        List<BookDTO> bookList =
                bookPage.stream()
                        .map(Book::toBookDTO)
                        .toList();
        return PageResponseDTO.<BookDTO>builder()
                .content(bookList)
                .page(page)
                .size(size)
                .totalElements(bookPage.getTotalElements())
                .totalPages(bookPage.getTotalPages())
                .build();
    }

    @Override
    public List<BookDTO> findAllByAuthorId(String authorId) {
        return bookRepository
                .findBookByAuthorId(authorId)
                .stream().map(Book::toBookDTO)
                .toList();
    }

    @Override
    public List<BookDTO> findAllByCategoryId(String categoryId) {
        return bookRepository
                .findAllByCategoryId(categoryId,null)
                .stream().map(Book::toBookDTO)
                .toList();
    }

    private Book getBook(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new NotFoundException("Book not found");
        }
        return bookOptional.get();
    }
}
