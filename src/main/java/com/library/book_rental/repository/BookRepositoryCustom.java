package com.library.book_rental.repository;

import com.library.book_rental.dto.BookSearchRequest;
import com.library.book_rental.entity.Book;
import java.util.List;

public interface BookRepositoryCustom {
    List<Book> search(BookSearchRequest request);
}
