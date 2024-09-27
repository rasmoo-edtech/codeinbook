package com.rasmoo.codeinbook.infrastructure.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${spring.kafka.bootstrap-servers:localhost:9092")
    private String bootstrapAddress;

    @Bean
    public <T>ProducerFactory<String, T> getGenericProducerFactory(Class<? extends Serializer> clazz) {
        return new DefaultKafkaProducerFactory<>(getProducerProperties(clazz));
    }

    @Bean
    public <T>KafkaTemplate<String, T> getKafkaTemplate() {
        return new KafkaTemplate<>(getGenericProducerFactory(JsonSerializer.class));
    }

    @Bean
    public NewTopic paymentTopic() {
        return new NewTopic("payment-topic", 1, (short) 1);
    }


    private Map<String, Object> getProducerProperties(Class<? extends  Serializer> clazz) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, clazz);

        return props;
    }

}
