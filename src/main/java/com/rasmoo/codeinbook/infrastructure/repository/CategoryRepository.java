package com.rasmoo.codeinbook.infrastructure.repository;

import com.rasmoo.codeinbook.common.enums.CategoryType;
import com.rasmoo.codeinbook.infrastructure.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findAllByCategoryType(CategoryType categoryType);

    List<Category> findAllByPrimaryCategoryId(String categoryId);

}
