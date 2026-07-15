package com.library.book_rental.controller;
import com.library.book_rental.entity.Book;
import com.library.book_rental.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("/api/books/search")
    public List<Book> searchBooks(@RequestParam String title) {
        return bookService.searchBooks(title);
    }
}
