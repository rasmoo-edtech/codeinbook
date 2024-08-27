package com.rasmoo.codeinbook.domain.service;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.response.PageResponseDTO;
import com.rasmoo.codeinbook.domain.port.in.BookServicePort;
import com.rasmoo.codeinbook.domain.port.out.AuthorRepositoryPort;
import com.rasmoo.codeinbook.domain.port.out.BookRepositoryPort;
import com.rasmoo.codeinbook.domain.port.out.CategoryRepositoryPort;

import static java.util.Objects.nonNull;

public class BookService implements BookServicePort {

    private final BookRepositoryPort bookRepositoryPort;

    private final CategoryRepositoryPort categoryRepositoryPort;

    private final AuthorRepositoryPort authorRepositoryPort;

    public BookService(BookRepositoryPort bookRepositoryPort,
                       CategoryRepositoryPort categoryRepositoryPort,
                       AuthorRepositoryPort authorRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
        this.categoryRepositoryPort = categoryRepositoryPort;
        this.authorRepositoryPort = authorRepositoryPort;
    }

    public BookDTO create(BookDTO dto) {
        checkCatogory(dto.categoryId());
        checkAuthor(dto.authorId());
        return bookRepositoryPort.create(dto);
    }

    public void update(String id, BookDTO dto) {
        checkCatogory(dto.categoryId());
        checkAuthor(dto.authorId());
        bookRepositoryPort.update(id, dto);
    }

    public BookDTO findById(String id) {
        return bookRepositoryPort.findById(id);
    }

    public void deleteById(String id) {
        bookRepositoryPort.deleteById(id);
    }

    public PageResponseDTO<BookDTO> findAll(int page, int size, String categoryId) {
        return bookRepositoryPort.findAll(page, size, categoryId);
    }

    private void checkCatogory(String categoryId) {
        if (nonNull(categoryId) && !categoryId.isBlank()) {
            categoryRepositoryPort.findById(categoryId);
        }
    }

    private void checkAuthor(String authorId) {
        if (nonNull(authorId)) {
            authorRepositoryPort.findById(authorId);
        }
    }
}
