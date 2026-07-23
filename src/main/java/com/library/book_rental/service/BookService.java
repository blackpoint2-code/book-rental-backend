package com.library.book_rental.service;

import com.library.book_rental.dto.BookCreateRequest;
import com.library.book_rental.dto.BookSearchRequest;
import com.library.book_rental.entity.Author;
import com.library.book_rental.entity.Book;
import com.library.book_rental.entity.BookStatus;
import com.library.book_rental.repository.AuthorRepository;
import com.library.book_rental.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    // 도서 대출 예약 로직
    public void reserveBook(Long bookId) {
        // 1. 책 ID로 책 조회 (없으면 예외 처리)
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다. ID: " + bookId));

        // 2. 현재 대출 가능한 상태인지 검증
        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new IllegalStateException("이미 대출 중이거나 예약된 도서입니다.");
        }

        // 3. 상태를 '예약중(RESERVED)'으로 변경
        // (Entity 내부에 status를 바꾸는 메서드를 만들어두거나 setter를 활용)
        book.setStatus(BookStatus.RESERVED);

        // @Transactional에 의해 메서드가 끝날 때 알아서 변경사항이 DB에 반영(Dirty Checking)됩니다.
    }
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
    private final AuthorRepository authorRepository;
    // 책 등록 로직
    public Long createBook(BookCreateRequest request) {
        // 1. 저자가 이미 DB에 있는지 확인 (없으면 새로 생성해서 저장)
        Author author = authorRepository.findByAuthorName(request.getAuthorName())
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setAuthorName(request.getAuthorName());
                    return authorRepository.save(newAuthor);
                });

        // 2. 책 엔티티 생성 및 저자 연결 (JOIN의 핵심!)
        Book book = new Book();
        book.setBookTitle(request.getBookTitle());
        book.setAuthor(author); // 저자 객체 세팅 (author_id 외래키로 저장됨)

        // 3. 책 저장
        Book savedBook = bookRepository.save(book);

        return savedBook.getId(); // 저장된 책의 ID 반환
    }
}