package com.rasmoo.codeinbook.application.configuration;

import com.rasmoo.codeinbook.domain.port.out.producer.EventProducerPort;
import com.rasmoo.codeinbook.domain.port.out.producer.MessageBrokerPort;
import com.rasmoo.codeinbook.domain.port.out.repository.AuthorRepositoryPort;
import com.rasmoo.codeinbook.domain.port.out.repository.BookRepositoryPort;
import com.rasmoo.codeinbook.domain.port.out.repository.CategoryRepositoryPort;
import com.rasmoo.codeinbook.domain.port.out.repository.PaymentRepositoryPort;
import com.rasmoo.codeinbook.domain.service.AuthorService;
import com.rasmoo.codeinbook.domain.service.BookService;
import com.rasmoo.codeinbook.domain.service.CategoryService;
import com.rasmoo.codeinbook.domain.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public BookService bookService(BookRepositoryPort bookRepositoryPort,
                                   CategoryRepositoryPort categoryRepositoryPort,
                                   AuthorRepositoryPort authorRepositoryPort) {
        return new BookService(bookRepositoryPort, categoryRepositoryPort, authorRepositoryPort);
    }

    @Bean
    public AuthorService authorService(AuthorRepositoryPort authorRepositoryPort, BookRepositoryPort bookRepositoryPort) {
        return new AuthorService(authorRepositoryPort, bookRepositoryPort);
    }

    @Bean
    public CategoryService categoryService(CategoryRepositoryPort categoryRepositoryPort,
                                           EventProducerPort eventProducerPort) {
        return new CategoryService(categoryRepositoryPort, eventProducerPort);
    }

    @Bean
    public PaymentService paymentService(PaymentRepositoryPort paymentRepositoryPort,
                                         MessageBrokerPort messageBrokerPort) {
        return new PaymentService(paymentRepositoryPort, messageBrokerPort);
    }

}
