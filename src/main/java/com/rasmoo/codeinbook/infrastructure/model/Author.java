package com.rasmoo.codeinbook.infrastructure.model;

import com.rasmoo.codeinbook.common.dto.AuthorDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static java.util.Objects.nonNull;

@Document("authors")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    private String id;

    private String name;

    private String resume;

    public void with(AuthorDTO dto) {
        this.id = nonNull(dto.id()) ? dto.id() : this.id;
        this.name = nonNull(dto.name()) ? dto.name() : this.name;
        this.resume = nonNull(dto.resume()) ? dto.resume() : this.resume;

    }

    public AuthorDTO toAuthorDTO() {
        return new AuthorDTO(id, name, resume);
    }

}
