package com.rasmoo.codeinbook.application.adapter.out;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.PageDTO;
import com.rasmoo.codeinbook.domain.port.out.BookRepositoryPort;
import com.rasmoo.codeinbook.infrastructure.repository.BookRepository;

public class BookRepositoryAdapter implements BookRepositoryPort {

    private BookRepository bookRepository;
    @Override
    public BookDTO create(BookDTO dto) {
        return null;
    }

    @Override
    public void update(String id, BookDTO dto) {

    }

    @Override
    public BookDTO findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public PageDTO<BookDTO> findAll(int page, int size) {
        return null;
    }
}
