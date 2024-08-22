package com.rasmoo.codeinbook.application.adapter.in.controller;

import com.rasmoo.codeinbook.domain.port.in.BookServicePort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookServicePort bookServicePort;

    public BookController(BookServicePort bookServicePort){
        this.bookServicePort = bookServicePort;
    }


}
