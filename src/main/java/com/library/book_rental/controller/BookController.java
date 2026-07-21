package com.library.book_rental.controller;
import com.library.book_rental.dto.BookCreateRequest;
import com.library.book_rental.dto.BookSearchRequest;
import com.library.book_rental.entity.Book;
import com.library.book_rental.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Validated
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("/api/books/search")
    public List<Book> search(@Valid BookSearchRequest request) {
        return bookService.searchBooks(request);
    }
    @PostMapping
    public ResponseEntity<Long> createBook(@RequestBody BookCreateRequest request) {
        Long bookId = bookService.createBook(request);
        return ResponseEntity.ok(bookId); // 성공적으로 저장된 책의 ID 응답
    }
}
