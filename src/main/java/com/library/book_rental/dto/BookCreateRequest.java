package com.library.book_rental.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequest {
    private String bookTitle; // 책 제목
    private String authorName; // 저자 이름
}
