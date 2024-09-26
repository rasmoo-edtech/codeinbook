package com.rasmoo.codeinbook.domain.port.out.producer;

import com.rasmoo.codeinbook.common.dto.PaymentDTO;

public interface MessageBrokerPort {

    void sendPaymentTopic(PaymentDTO message);

}
