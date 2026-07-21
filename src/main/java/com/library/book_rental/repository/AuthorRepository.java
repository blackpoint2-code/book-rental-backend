package com.library.book_rental.repository;

import com.library.book_rental.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // 저자 이름으로 이미 등록된 저자가 있는지 찾기 위한 메서드
    Optional<Author> findByAuthorName(String authorName);
}
