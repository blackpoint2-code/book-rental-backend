package com.library.book_rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.library.book_rental.repository.BookRepository; // 이거 꼭 import 해야 해요!
import jakarta.annotation.PostConstruct; // 이것도 필요해요!
import org.springframework.beans.factory.annotation.Autowired; // 이것도!
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookRentalApplication {

	@Autowired
	BookRepository bookRepository; // 의존성 주입

	public static void main(String[] args) {
		SpringApplication.run(BookRentalApplication.class, args);
	}

	@PostConstruct
	public void init() {
		long count = bookRepository.count();
		System.out.println("========================================");
		System.out.println("현재 DB에 들어있는 책의 개수: " + count);
		System.out.println("========================================");
	}
}
