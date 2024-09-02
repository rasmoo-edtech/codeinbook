package com.rasmoo.codeinbook.infrastructure.event;

import org.springframework.context.ApplicationEvent;

public class CategoryDeletedEvent extends ApplicationEvent {

    public CategoryDeletedEvent(Object souce) {
        super(souce);
    }
}
