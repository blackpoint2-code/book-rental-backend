package com.library.book_rental.scheduler;


import com.library.book_rental.entity.Rental;
import com.library.book_rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RentalScheduler {

    private final RentalService rentalService;

    // 매일 오전 9시에 실행 (초 분 시 일 월 요일)
    @Scheduled(cron = "0 * * * * *")
    public void sendReturnReminder() {
        log.info("테스트");
        List<Rental> dueList = rentalService.getDueTomorrowRentals();

        if (dueList.isEmpty()) {
            log.info("알림 대상자가 없습니다.");
        } else {
            for (Rental rental : dueList) {
                log.info("알림 발송: {}님, 책({})의 반납 예정일이 내일입니다.",
                        rental.getMemberName(), rental.getBookNo());
            }
        }
    }
}
