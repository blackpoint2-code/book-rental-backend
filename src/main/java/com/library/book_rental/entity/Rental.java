package com.library.book_rental.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity                      // 이 클래스가 DB 테이블과 연결됨을 알림
@Table(name = "rental")       // pgAdmin에서 만든 테이블 이름
@Getter                      // Lombok: getter 자동 생성
@Setter                      // Lombok: setter 자동 생성
@NoArgsConstructor           // 기본 생성자 생성
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 (PK)
    private Long id;

    @Column(name = "book_no", unique = true, nullable = false)
    private String bookNo;

    String memberName;
    LocalDate dueDate;
}
