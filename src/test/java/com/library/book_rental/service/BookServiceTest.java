package com.library.book_rental.service;

import com.library.book_rental.entity.Book;
import com.library.book_rental.entity.BookStatus;
import com.library.book_rental.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;
    @Test
    @DisplayName("도서 대출 예약 성공")
    void reserveBook() {
        Book book = new Book();
        book.setBookTitle("테스트용 책");
        Book savedBook = bookRepository.save(book);

        // when: 서비스의 예약 메서드를 호출한다
        bookService.reserveBook(savedBook.getId());

        // then: 책의 상태가 RESERVED로 바뀌었는지 확인한다
        Book findBook = bookRepository.findById(savedBook.getId()).get();
        assertThat(findBook.getStatus()).isEqualTo(BookStatus.RESERVED);
    }
    @Test
    @DisplayName("이미 대출/예약된 책이면 예외 발생")
    void reserveBook_fail() {
        // given: 이미 RESERVED 상태인 책을 만들어 둔다
        Book book = new Book();
        book.setBookTitle("이미 예약된 책");
        book.setStatus(BookStatus.RESERVED);
        Book savedBook = bookRepository.save(book);

        // when & then: 예약 메서드를 불렀을 때 IllegalStateException 예외가 터지는지 검증한다
        assertThrows(IllegalStateException.class, () -> {
            bookService.reserveBook(savedBook.getId());
        });
    }
    @Test
    @DisplayName("전체 도서 목록 조회")
    void getAllBooks() {
        Book book1 = new Book();
        book1.setBookTitle("자바의 정석");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setBookTitle("클린 코드");
        bookRepository.save(book2);

        // when: 서비스의 전체 조회 메서드를 호출한다
        List<Book> books = bookService.getAllBooks();

        // then: 저장한 책들이 목록에 잘 포함되어 있는지 확인한다
        assertThat(books.size()).isGreaterThanOrEqualTo(2);
    }
    @Test
    @DisplayName("전체 도서 목록 조회")
    void searchBooks() {
        // given: 테스트용 책 2권을 미리 저장해 둔다
        Book book1 = new Book();
        book1.setBookTitle("자바의 정석");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setBookTitle("클린 코드");
        bookRepository.save(book2);

        // when: 서비스의 전체 조회 메서드를 호출한다
        List<Book> books = bookService.getAllBooks();

        // then: 저장한 책들이 목록에 잘 포함되어 있는지 확인한다
        assertThat(books.size()).isGreaterThanOrEqualTo(2);
    }
    @Test
    @DisplayName("신규 도서 등록")
    void createBook() {

    }
}