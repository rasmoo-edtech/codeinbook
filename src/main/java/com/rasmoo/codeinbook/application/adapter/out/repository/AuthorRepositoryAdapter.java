package com.rasmoo.codeinbook.application.adapter.out.repository;

import com.rasmoo.codeinbook.common.dto.AuthorDTO;
import com.rasmoo.codeinbook.common.dto.response.PageResponseDTO;
import com.rasmoo.codeinbook.common.enums.SortDirection;
import com.rasmoo.codeinbook.common.exception.BadRequestException;
import com.rasmoo.codeinbook.common.exception.NotFoundException;
import com.rasmoo.codeinbook.domain.port.out.AuthorRepositoryPort;
import com.rasmoo.codeinbook.infrastructure.model.Author;
import com.rasmoo.codeinbook.infrastructure.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    public AuthorDTO findById(String id) {
        return getAuthorById(id).toAuthorDTO();
    }

    @Override
    public PageResponseDTO<AuthorDTO> findAllByName(String name, int page, int size, SortDirection sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(sort.name()), "name");
        Page<Author> authorPage = authorRepository.findAllByNameContainsIgnoreCase(name, pageRequest);
        List<AuthorDTO> authorList =
                authorPage.stream()
                        .map(Author::toAuthorDTO)
                        .toList();
        return PageResponseDTO.<AuthorDTO>builder()
                .content(authorList)
                .totalPages(authorPage.getTotalPages())
                .totalElements(authorPage.getTotalElements())
                .page(page)
                .size(size)
                .build();
    }

    private Author getAuthorById(String id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isEmpty()) {
            throw new NotFoundException("Author not found");
        }
        return authorOptional.get();
    }


}
