package com.rasmoo.codeinbook.domain.port.in;

import com.rasmoo.codeinbook.common.dto.AuthorDTO;
import com.rasmoo.codeinbook.common.dto.PageDTO;
import com.rasmoo.codeinbook.common.enums.Sort;

public interface AuthorServicePort {

    AuthorDTO create(AuthorDTO authorDTO);

    PageDTO<AuthorDTO> findAllByName(String name, int page, int size, Sort sort);

    void deleteById(String id);
}
