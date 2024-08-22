package com.rasmoo.codeinbook.application.adapter.in.controller;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.PageDTO;
import com.rasmoo.codeinbook.domain.port.in.BookServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookServicePort bookServicePort;

    public BookController(BookServicePort bookServicePort){
        this.bookServicePort = bookServicePort;
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO dto ) {
        return ResponseEntity.status(CREATED).body(bookServicePort.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody BookDTO dto, @PathVariable("id") String id) {
        bookServicePort.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable("id") String id ) {
        return ResponseEntity.status(OK).body(bookServicePort.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id  ) {
        bookServicePort.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PageDTO<BookDTO>> findAll(@RequestParam("page")int page, @RequestParam("size")int size) {
        return ResponseEntity.status(OK).body(bookServicePort.findAll(page, size));
    }

}
