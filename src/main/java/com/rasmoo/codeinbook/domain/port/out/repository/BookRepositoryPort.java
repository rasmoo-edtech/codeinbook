package com.rasmoo.codeinbook.domain.port.out.repository;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.response.PageResponseDTO;

import java.util.List;

public interface BookRepositoryPort {

    BookDTO create(BookDTO dto);

    void update(String id, BookDTO dto, String categoryId);

    BookDTO findById(String id);

    void deleteById(String id);

    PageResponseDTO<BookDTO> findAll(int page, int size, String categoryId);

    List<BookDTO> findAllByAuthorId(String authorId);

    List<BookDTO> findAllByCategoryId(String categoryId);
}
