package com.rasmoo.codeinbook.domain.port.out;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.response.PageResponseDTO;

import java.util.List;

public interface BookRepositoryPort {

    BookDTO create(BookDTO dto);

    void update(String id, BookDTO dto);

    BookDTO findById(String id);

    void deleteById(String id);

    PageResponseDTO<BookDTO> findAll(int page, int size);

    List<BookDTO> findAllByAuthorId(String authorId);
}
