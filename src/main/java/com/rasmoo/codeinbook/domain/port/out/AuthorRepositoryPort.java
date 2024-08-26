package com.rasmoo.codeinbook.domain.port.out;

import com.rasmoo.codeinbook.common.dto.AuthorDTO;
import com.rasmoo.codeinbook.common.dto.response.PageResponseDTO;
import com.rasmoo.codeinbook.common.enums.SortDirection;

public interface AuthorRepositoryPort {

    AuthorDTO create(AuthorDTO dto);

    void deleteById(String id);

    PageResponseDTO<AuthorDTO> findAllByName(String name, int page, int size, SortDirection sort);
}
