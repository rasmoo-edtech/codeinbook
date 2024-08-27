package com.rasmoo.codeinbook.application.adapter.in.controller;

import com.rasmoo.codeinbook.common.dto.CategoryDTO;
import com.rasmoo.codeinbook.common.dto.response.CategoryResponseDTO;
import com.rasmoo.codeinbook.domain.port.in.CategoryServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryServicePort categoryServicePort;

    public CategoryController(CategoryServicePort categoryServicePort) {
        this.categoryServicePort = categoryServicePort;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(CategoryDTO dto) {
        return ResponseEntity.status(CREATED).body(categoryServicePort.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        return ResponseEntity.status(OK).body(categoryServicePort.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        categoryServicePort.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
