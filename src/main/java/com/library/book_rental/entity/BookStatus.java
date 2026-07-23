package com.library.book_rental.entity;

// 도서 상태 Enum
public enum BookStatus {
    AVAILABLE, // 대출 가능
    RESERVED,  // 대출 예약중
    RENTED     // 대출중
}
