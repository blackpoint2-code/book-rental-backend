package com.library.book_rental.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookSearchRequest {
    @Size(min = 1, max = 50, message = "제목은 1자 이상 50자 이하로 입력해주세요.")
    private String title;
    private String author;
    private String publisher;
}
