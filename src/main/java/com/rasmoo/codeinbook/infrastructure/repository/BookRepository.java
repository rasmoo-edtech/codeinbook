package com.rasmoo.codeinbook.infrastructure.repository;

import com.rasmoo.codeinbook.infrastructure.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findBookByAuthorId(String authorId);

    Page<Book> findAllByCategoryId(String id, PageRequest pageRequest);

}
