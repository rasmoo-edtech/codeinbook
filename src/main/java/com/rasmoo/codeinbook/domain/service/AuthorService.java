package com.rasmoo.codeinbook.domain.service;

import com.rasmoo.codeinbook.common.dto.AuthorDTO;
import com.rasmoo.codeinbook.common.dto.PageDTO;
import com.rasmoo.codeinbook.common.enums.Sort;
import com.rasmoo.codeinbook.common.exception.BusinessException;
import com.rasmoo.codeinbook.domain.port.in.AuthorServicePort;
import com.rasmoo.codeinbook.domain.port.out.AuthorRepositoryPort;
import com.rasmoo.codeinbook.domain.port.out.BookRepositoryPort;

public class AuthorService implements AuthorServicePort {

    private AuthorRepositoryPort authorRepositoryPort;

    private BookRepositoryPort bookRepositoryPort;

    public AuthorService(AuthorRepositoryPort authorRepositoryPort, BookRepositoryPort bookRepositoryPort) {
        this.authorRepositoryPort = authorRepositoryPort;
        this.bookRepositoryPort = bookRepositoryPort;
    }


    @Override
    public AuthorDTO create(AuthorDTO authorDTO) {
        return authorRepositoryPort.create(authorDTO);
    }

    @Override
    public PageDTO<AuthorDTO> findAllByName(String name, int page, int size, Sort sort) {
        return authorRepositoryPort.findAllByName(name, page, size, sort);
    }

    @Override
    public void deleteById(String id) {
        if (bookRepositoryPort.findAllByAuthorId(id).isEmpty()) {
            throw  new BusinessException("Author can not be deleted because there is one or more books saved");
        }
        authorRepositoryPort.deleteById(id);
    }
}
