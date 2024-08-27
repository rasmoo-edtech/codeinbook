package com.rasmoo.codeinbook.infrastructure.model;

import com.rasmoo.codeinbook.common.dto.CategoryDTO;
import com.rasmoo.codeinbook.common.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import static java.util.Objects.nonNull;

@Document("categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Category {

    @Id
    private String id;

    private String name;

    @Field("category_type")
    private CategoryType categoryType;

    @Field("primary_category")
    @DBRef
    private Category primaryCategory;

    public void with(CategoryDTO dto, Category primaryCategory) {
        this.id = nonNull(dto.id()) ? dto.id() : this.id;
        this.name = nonNull(dto.name()) ? dto.name() : this.name;
        this.categoryType = nonNull(dto.categoryType()) ? dto.categoryType() : this.categoryType;
        this.primaryCategory = nonNull(primaryCategory) ? primaryCategory : this.primaryCategory;
    }

    public CategoryDTO toCategoryDTO() {
        return new CategoryDTO(id, name, categoryType,  nonNull(primaryCategory) ? primaryCategory.id : null);
    }

}
