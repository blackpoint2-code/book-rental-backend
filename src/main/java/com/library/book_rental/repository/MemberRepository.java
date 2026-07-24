package com.library.book_rental.repository;

import com.library.book_rental.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 로그인할 때 이메일 존재 여부를 확인하기 위함
    Optional<Member> findByEmail(String email);
}
