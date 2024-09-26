package com.rasmoo.codeinbook.domain.port.out.repository;

import com.rasmoo.codeinbook.common.dto.PaymentDTO;

public interface PaymentRepositoryPort {

    PaymentDTO save(PaymentDTO dto);

}
