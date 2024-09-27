package com.rasmoo.codeinbook.domain.service;

import com.rasmoo.codeinbook.common.dto.PaymentDTO;
import com.rasmoo.codeinbook.domain.port.in.PaymentServicePort;
import com.rasmoo.codeinbook.domain.port.out.producer.MessageBrokerPort;
import com.rasmoo.codeinbook.domain.port.out.repository.PaymentRepositoryPort;

public class PaymentService implements PaymentServicePort {

    private final PaymentRepositoryPort paymentRepositoryPort;

    private final MessageBrokerPort paymentProducer;

    public PaymentService(PaymentRepositoryPort paymentRepositoryPort,
                          MessageBrokerPort messageBrokerPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
        this.paymentProducer = messageBrokerPort;
    }

    public PaymentDTO processPayment(PaymentDTO dto) {
        var paymentSaved = paymentRepositoryPort.save(dto);
        paymentProducer.sendToTopic(paymentSaved);
        return paymentSaved;
    }

}
