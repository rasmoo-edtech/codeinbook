package com.rasmoo.codeinbook.application.configuration;

import com.rasmoo.codeinbook.domain.port.out.AuthorRepositoryPort;
import com.rasmoo.codeinbook.domain.port.out.BookRepositoryPort;
import com.rasmoo.codeinbook.domain.service.AuthorService;
import com.rasmoo.codeinbook.domain.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public BookService bookService(BookRepositoryPort bookRepositoryPort) {
        return new BookService(bookRepositoryPort);
    }

    @Bean
    public AuthorService authorService(AuthorRepositoryPort authorRepositoryPort, BookRepositoryPort bookRepositoryPort) {
        return new AuthorService(authorRepositoryPort,bookRepositoryPort);
    }


}
