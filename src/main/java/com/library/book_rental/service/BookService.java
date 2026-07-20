package com.library.book_rental.service;

import com.library.book_rental.dto.BookSearchRequest;
import com.library.book_rental.entity.Book;
import com.library.book_rental.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    // 모든 책을 가져오는 메서드
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public List<Book> searchBooks(BookSearchRequest request) {
        if (request == null) {
            // 혹은 빈 검색 객체를 새로 생성해서 넘기거나 예외를 던짐
            throw new IllegalArgumentException("검색 요청 정보가 없습니다.");
        }
        return bookRepository.search(request);
    }
}
