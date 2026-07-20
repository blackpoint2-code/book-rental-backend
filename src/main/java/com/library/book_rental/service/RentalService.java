package com.library.book_rental.service;

import com.library.book_rental.entity.Rental;
import com.library.book_rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;

    public List<Rental> getDueTomorrowRentals() {
        // 오늘 날짜 + 1일 = 내일
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        return rentalRepository.findByDueDate(tomorrow);
    }
}
