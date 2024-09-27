package com.rasmoo.codeinbook.application.adapter.out.repository;

import com.rasmoo.codeinbook.common.dto.PaymentDTO;
import com.rasmoo.codeinbook.common.enums.PaymentStatus;
import com.rasmoo.codeinbook.domain.port.out.repository.PaymentRepositoryPort;
import com.rasmoo.codeinbook.infrastructure.model.Payment;
import com.rasmoo.codeinbook.infrastructure.repository.PaymentRepository;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final PaymentRepository paymentRepository;

    public PaymentRepositoryAdapter(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDTO save(PaymentDTO dto) {
        Payment payment = new Payment();
        payment.with(dto, PaymentStatus.PROCESSING);
        return paymentRepository.save(payment).toPaymentoDTO();
    }
}
