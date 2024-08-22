package com.rasmoo.codeinbook.domain.service;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.PageDTO;
import com.rasmoo.codeinbook.domain.port.out.BookRepositoryPort;

public class BookService {

    private BookRepositoryPort bookRepositoryPort;

    private BookService(BookRepositoryPort bookRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
    }

    public BookDTO create(BookDTO dto) {
        return bookRepositoryPort.create(dto);
    }

    public void update(String id, BookDTO dto) {
        bookRepositoryPort.update(id, dto);
    }


    public BookDTO findById(String id) {
        return bookRepositoryPort.findById(id);
    }

    public void delete(String id) {
        bookRepositoryPort.delete(id);
    }

    public PageDTO<BookDTO> findAll(int page, int size) {
        return bookRepositoryPort.findAll(page, size);
    }
}
