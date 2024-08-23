package com.rasmoo.codeinbook.domain.port.out;

import com.rasmoo.codeinbook.common.dto.AuthorDTO;
import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.PageDTO;
import com.rasmoo.codeinbook.common.enums.Sort;

public interface AuthorRepositoryPort {

    AuthorDTO create(AuthorDTO dto);

    void deleteById(String id);

    PageDTO<AuthorDTO> findAllByName(String name, int page, int size, Sort sort);
}
