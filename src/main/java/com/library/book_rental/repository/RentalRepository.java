package com.library.book_rental.repository;

import com.library.book_rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    // "dueDate가 특정 날짜와 같은 데이터를 모두 찾아라"
    List<Rental> findByDueDate(LocalDate dueDate);
}
