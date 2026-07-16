package com.library.book_rental.repository;

import com.library.book_rental.dto.BookSearchRequest;
import com.library.book_rental.entity.Book;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.library.book_rental.entity.QBook.book;
import java.util.List;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Book> search(BookSearchRequest request) {
        BooleanBuilder builder = new BooleanBuilder();

        // 조건이 있을 때만 쿼리에 추가 (동적 쿼리)
        if (request.getTitle() != null && !request.getTitle().isEmpty()) {
            builder.and(book.title.contains(request.getTitle()));
        }
        if (request.getAuthor() != null && !request.getAuthor().isEmpty()) {
            builder.and(book.author.contains(request.getAuthor()));
        }
        if (request.getPublisher() != null && !request.getPublisher().isEmpty()) {
            builder.and(book.publisher.contains(request.getPublisher()));
        }

        return queryFactory.selectFrom(book)
                .where(builder)
                .fetch();
    }
}
