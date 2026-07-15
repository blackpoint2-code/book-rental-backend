package com.library.book_rental.service;

import com.library.book_rental.entity.Book;
import com.library.book_rental.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    // 모든 책을 가져오는 메서드
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public List<Book> searchBooks(String title) {
        return bookRepository.findByTitleContaining(title);
    }
}
