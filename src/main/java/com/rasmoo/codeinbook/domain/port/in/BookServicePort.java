package com.rasmoo.codeinbook.domain.port.in;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.response.PageResponseDTO;

public interface BookServicePort {

     BookDTO create(BookDTO dto);

     void update(String id, BookDTO dto);


     BookDTO findById(String id);

     void deleteById(String id);

    PageResponseDTO<BookDTO> findAll(int page, int size);

}
