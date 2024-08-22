package com.rasmoo.codeinbook.infrastructure.repository;

import com.rasmoo.codeinbook.infrastructure.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
