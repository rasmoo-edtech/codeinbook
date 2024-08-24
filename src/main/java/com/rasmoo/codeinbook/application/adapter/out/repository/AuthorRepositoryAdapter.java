package com.rasmoo.codeinbook.application.adapter.out.repository;

import com.rasmoo.codeinbook.common.dto.AuthorDTO;
import com.rasmoo.codeinbook.common.dto.PageDTO;
import com.rasmoo.codeinbook.common.enums.SortDirection;
import com.rasmoo.codeinbook.common.exception.BadRequestException;
import com.rasmoo.codeinbook.domain.port.out.AuthorRepositoryPort;
import com.rasmoo.codeinbook.infrastructure.model.Author;
import com.rasmoo.codeinbook.infrastructure.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorRepositoryAdapter implements AuthorRepositoryPort {

    private final AuthorRepository authorRepository;

    public AuthorRepositoryAdapter(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDTO create(AuthorDTO dto) {
        Author author = new Author();
        if (!dto.name().contains(" ")) {
            throw new BadRequestException("Author's last name must be informed");
        }
        author.with(dto);
        return authorRepository.save(author).toAuthorDTO();
    }

    @Override
    public void deleteById(String id) {
        authorRepository.deleteById(id);
    }

    @Override
    public PageDTO<AuthorDTO> findAllByName(String name, int page, int size, SortDirection sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(sort.name()));
        Page<Author> authorPage = authorRepository.findAllByNameIgnoreCase(name,pageRequest);
        List<AuthorDTO> authorList =
                authorPage.stream()
                        .map(Author::toAuthorDTO)
                        .toList();
        return PageDTO.<AuthorDTO>builder()
                .content(authorList)
                .totalPages(authorPage.getTotalPages())
                .totalElements(authorPage.getTotalElements())
                .page(page)
                .size(size)
                .build();
    }
}
