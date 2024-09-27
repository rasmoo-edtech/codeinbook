package com.rasmoo.codeinbook.application.adapter.out.producer;

import com.rasmoo.codeinbook.common.dto.PaymentDTO;
import com.rasmoo.codeinbook.domain.port.out.producer.MessageBrokerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Qualifier("paymentProducer")
@RequiredArgsConstructor
public class PaymentProducerAdapter implements MessageBrokerPort {

    private final KafkaTemplate<String, PaymentDTO> kafkaTemplate;

    @Override
    public void sendToTopic(Object message) {
        PaymentDTO paymentDTO = (PaymentDTO) message;
        kafkaTemplate.send("payment-topic", paymentDTO);
    }
}
