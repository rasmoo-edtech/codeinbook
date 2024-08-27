package com.rasmoo.codeinbook.application.adapter.in.controller;

import com.rasmoo.codeinbook.common.dto.AuthorDTO;
import com.rasmoo.codeinbook.common.dto.response.PageResponseDTO;
import com.rasmoo.codeinbook.common.enums.SortDirection;
import com.rasmoo.codeinbook.domain.port.in.AuthorServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorServicePort authorServicePort;

    public AuthorController(AuthorServicePort authorServicePort) {
        this.authorServicePort = authorServicePort;
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorDTO dto) {
        return ResponseEntity.status(CREATED).body(authorServicePort.create(dto));
    }

    @GetMapping("/{name}")
    public ResponseEntity<PageResponseDTO<AuthorDTO>> findByName(@PathVariable("name") String name,
                                                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                                                 @RequestParam(value = "sort", required = false,
                                                                         defaultValue = "ASC") String sort) {
        return ResponseEntity.status(OK).body(authorServicePort.findAllByName(name, page, size, SortDirection.valueOf(sort.toUpperCase())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        authorServicePort.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
