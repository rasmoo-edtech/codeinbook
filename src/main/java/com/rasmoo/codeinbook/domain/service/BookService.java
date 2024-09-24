package com.rasmoo.codeinbook.domain.service;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.CategoryBookEventDTO;
import com.rasmoo.codeinbook.common.dto.response.PageResponseDTO;
import com.rasmoo.codeinbook.domain.port.in.BookServicePort;
import com.rasmoo.codeinbook.domain.port.out.repository.AuthorRepositoryPort;
import com.rasmoo.codeinbook.domain.port.out.repository.BookRepositoryPort;
import com.rasmoo.codeinbook.domain.port.out.repository.CategoryRepositoryPort;

import java.util.List;

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
        bookRepositoryPort.update(id, dto, null);
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

    @Override
    public void updateAllCategoriesId(CategoryBookEventDTO eventDTO) {
        List<BookDTO> bookList = bookRepositoryPort.findAllByCategoryId(eventDTO.source());
        bookList
                .forEach(book -> bookRepositoryPort.update(book.id(), null, eventDTO.target()));
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
