package com.rasmoo.codeinbook.application.adapter.out.producer;

import com.rasmoo.codeinbook.common.dto.CategoryBookEventDTO;
import com.rasmoo.codeinbook.domain.port.out.producer.EventProducerPort;
import com.rasmoo.codeinbook.infrastructure.event.CategoryDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EventProducerAdapter implements EventProducerPort {

    private final ApplicationEventPublisher events;

    @Override
    @Transactional
    public void updateCategoryBook(CategoryBookEventDTO dto) {
        events.publishEvent(new CategoryDeletedEvent(dto));
    }


}
