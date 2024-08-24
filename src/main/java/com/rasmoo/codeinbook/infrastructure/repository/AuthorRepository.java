package com.rasmoo.codeinbook.infrastructure.repository;

import com.rasmoo.codeinbook.infrastructure.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    Page<Author> findAllByNameIgnoreCase(String name, PageRequest pageRequest);

}
