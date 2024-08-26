package com.rasmoo.codeinbook.infrastructure.model;

import com.rasmoo.codeinbook.common.dto.CategoryDTO;
import com.rasmoo.codeinbook.common.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import static java.util.Objects.nonNull;

@Document("categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    private String id;

    private String name;

    private CategoryType categoryType;

    @DBRef
    private Category primaryCategory;

    public void with(CategoryDTO dto, Category primaryCategory) {
        this.id = nonNull(dto.id()) ? dto.id() : this.id;
        this.name = nonNull(dto.name()) ? dto.name() : this.name;
        this.categoryType = nonNull(dto.categoryType()) ? dto.categoryType() : this.categoryType;
        this.primaryCategory = nonNull(primaryCategory) ? primaryCategory : this.primaryCategory;
    }

    public CategoryDTO categoryDTO() {
        return new CategoryDTO(id, name, categoryType, primaryCategory.id);
    }

}
