package com.rasmoo.codeinbook.domain.port.in;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.PageDTO;

public interface BookServicePort {

     BookDTO create(BookDTO dto);

     void update(String id, BookDTO dto);


     BookDTO findById(String id);

     void delete(String id);

    PageDTO<BookDTO> findAll(int page, int size);

}
