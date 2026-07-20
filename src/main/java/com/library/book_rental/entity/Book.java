package com.library.book_rental.entity;

import jakarta.persistence.*; // JPA 라이브러리 사용
import lombok.*;              // Getter/Setter 등을 편하게 사용

@Entity                      // 이 클래스가 DB 테이블과 연결됨을 알림
@Table(name = "books")       // pgAdmin에서 만든 테이블 이름
@Getter                      // Lombok: getter 자동 생성
@Setter                      // Lombok: setter 자동 생성
@NoArgsConstructor           // 기본 생성자 생성
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 (PK)
    private Long id;

    @Column(name = "book_no", unique = true, nullable = false)
    private String bookNo; // B-001, B-002...

    @Column(name = "book_title")
    private String bookTitle;

    private String title;
    private String author;
    private String publisher;
    private String category;

    @Column(name = "is_recommended")
    private boolean isRecommended;
}
