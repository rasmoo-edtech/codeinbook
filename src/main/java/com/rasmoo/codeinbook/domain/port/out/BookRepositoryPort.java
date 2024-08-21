package com.rasmoo.codeinbook.domain.port.out;

import com.rasmoo.codeinbook.common.dto.BookDTO;

public interface BookRepositoryPort {

    BookDTO create(BookDTO dto);

    void update(String id, BookDTO dto);

    BookDTO findById(String id);

    void delete(String id);
}
