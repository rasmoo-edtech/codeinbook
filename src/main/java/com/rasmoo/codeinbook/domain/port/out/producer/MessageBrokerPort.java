package com.rasmoo.codeinbook.domain.port.out.producer;

import com.rasmoo.codeinbook.common.dto.PaymentDTO;

import java.util.Objects;

public interface MessageBrokerPort {

    void sendToTopic(Object message);

}
