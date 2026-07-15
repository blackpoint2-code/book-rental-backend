package com.library.book_rental.service;

import com.library.book_rental.entity.Book;
import com.library.book_rental.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // 모든 책을 가져오는 메서드
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
