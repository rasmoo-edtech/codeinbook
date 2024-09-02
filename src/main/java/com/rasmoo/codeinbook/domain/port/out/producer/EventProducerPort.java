package com.rasmoo.codeinbook.domain.port.out.producer;

import com.rasmoo.codeinbook.common.dto.CategoryBookEventDTO;

public interface EventProducerPort {

    void updateCategoryBook(CategoryBookEventDTO dto);
}
