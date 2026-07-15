package com.library.book_rental.controller;
import com.library.book_rental.entity.Book;
import com.library.book_rental.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }
}
