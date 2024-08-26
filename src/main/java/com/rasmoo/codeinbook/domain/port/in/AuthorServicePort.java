package com.rasmoo.codeinbook.domain.port.in;

import com.rasmoo.codeinbook.common.dto.AuthorDTO;
import com.rasmoo.codeinbook.common.dto.response.PageResponseDTO;
import com.rasmoo.codeinbook.common.enums.SortDirection;

public interface AuthorServicePort {

    AuthorDTO create(AuthorDTO authorDTO);

    PageResponseDTO<AuthorDTO> findAllByName(String name, int page, int size, SortDirection sort);

    void deleteById(String id);
}
