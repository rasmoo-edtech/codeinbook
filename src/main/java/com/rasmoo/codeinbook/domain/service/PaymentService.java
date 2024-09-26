package com.rasmoo.codeinbook.domain.service;

import com.rasmoo.codeinbook.common.dto.PaymentDTO;
import com.rasmoo.codeinbook.domain.port.out.producer.MessageBrokerPort;
import com.rasmoo.codeinbook.domain.port.out.repository.PaymentRepositoryPort;

public class PaymentService {

    private PaymentRepositoryPort paymentRepositoryPort;

    private MessageBrokerPort messageBrokerPort;

    public PaymentService(PaymentRepositoryPort paymentRepositoryPort,
                          MessageBrokerPort messageBrokerPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
        this.messageBrokerPort = messageBrokerPort;
    }

    public PaymentDTO processPayment(PaymentDTO dto) {
        var paymentSaved = paymentRepositoryPort.save(dto);
        messageBrokerPort.sendPaymentTopic(paymentSaved);
        return paymentSaved;
    }

}
