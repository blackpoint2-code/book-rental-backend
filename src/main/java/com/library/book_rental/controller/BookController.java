package com.library.book_rental.controller;
import com.library.book_rental.dto.BookCreateRequest;
import com.library.book_rental.dto.BookSearchRequest;
import com.library.book_rental.entity.Book;
import com.library.book_rental.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
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
    @PostMapping("/api/books")
    public ResponseEntity<Long> createBook(@RequestBody BookCreateRequest request) {
        Long bookId = bookService.createBook(request);
        return ResponseEntity.ok(bookId); // 성공적으로 저장된 책의 ID 응답
    }
    // 도서 대출 예약 API
    @PostMapping("/{id}/reserve")
    public ResponseEntity<String> reserveBook(@PathVariable Long id) {
        try {
            bookService.reserveBook(id);
            return ResponseEntity.ok("도서 대출 예약이 완료되었습니다.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            // 잘못된 요청이나 상태일 경우 400 에러와 함께 메시지 전달
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
