package com.library.book_rental.controller;

import com.library.book_rental.dto.MemberDto;
import com.library.book_rental.entity.Member;
import com.library.book_rental.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // 프론트엔드(React)와의 CORS 허용
public class MemberController {
    private final MemberService memberService;

    // POST /api/members/signup - 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDto.SignupRequest request) {
        try {
            memberService.signup(request);
            return ResponseEntity.ok("회원가입 성공!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // POST /api/members/login - 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto.LoginRequest request) {
        try {
            Member member = memberService.login(request);
            // 로그인 성공 시 간단하게 유저 이름과 이메일 반환 (추후 세션이나 JWT로 고도화 가능)
            return ResponseEntity.ok(member.getName() + "님 환영합니다!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
