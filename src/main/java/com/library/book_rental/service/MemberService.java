package com.library.book_rental.service;

import com.library.book_rental.dto.MemberDto;
import com.library.book_rental.entity.Member;
import com.library.book_rental.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    // 1. 회원가입
    @Transactional
    public Long signup(MemberDto.SignupRequest request) {
        // 이메일 중복 검사
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }

        Member member = new Member();
        member.setEmail(request.getEmail());
        member.setPassword(request.getPassword()); // 실무에서는 암호화(BCrypt) 필수! 우선은 평문으로 갑니다.
        member.setName(request.getName());
        member.setRole("USER");

        memberRepository.save(member);
        return member.getId();
    }

    // 2. 로그인
    public Member login(MemberDto.LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!member.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return member; // 로그인 성공 시 회원 객체 반환
    }
}
