package com.rasmoo.codeinbook.application.adapter.in.listener;

import com.rasmoo.codeinbook.common.dto.CategoryBookEventDTO;
import com.rasmoo.codeinbook.domain.port.in.BookServicePort;
import com.rasmoo.codeinbook.infrastructure.event.CategoryDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class BookEventListener {

    private final BookServicePort bookServicePort;

    @TransactionalEventListener
    @Async
    public void onCategoryEventDeleted(CategoryDeletedEvent event) {
        CategoryBookEventDTO eventDTO = (CategoryBookEventDTO) event.getSource();
        bookServicePort.updateAllCategoriesId(eventDTO);
    }
}
