package com.rasmoo.codeinbook.infrastructure.model;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Document("books")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;

    private String title;

    private String subtitle;

    private String description;

    private BigDecimal price;

    public void with(BookDTO dto) {
        this.id = nonNull(dto.id()) ? dto.id() : this.id;
        this.title = nonNull(dto.title()) ? dto.title() : this.title;
        this.subtitle = nonNull(dto.subtitle()) ? dto.subtitle() : this.subtitle;
        this.description = nonNull(dto.description()) ? dto.description() : this.description;
        this.price = nonNull(dto.price()) ? dto.price() : this.price;
    }

    public BookDTO toBookDTO() {
        return new BookDTO(id, title, subtitle, description, price);
    }

}
