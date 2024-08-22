package com.rasmoo.codeinbook.application.adapter.configuration;

import com.rasmoo.codeinbook.domain.port.out.BookRepositoryPort;
import com.rasmoo.codeinbook.domain.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    @Bean
    public BookService bookService(BookRepositoryPort bookRepositoryPort) {
        return new BookService(bookRepositoryPort);
    }
}
