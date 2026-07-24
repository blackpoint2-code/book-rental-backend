package com.library.book_rental.dto;

import lombok.Getter;
import lombok.Setter;

public class MemberDto {
    // 회원가입 요청 DTO
    @Getter
    @Setter
    public static class SignupRequest {
        private String email;
        private String password;
        private String name;
    }

    // 로그인 요청 DTO
    @Getter @Setter
    public static class LoginRequest {
        private String email;
        private String password;
    }
}
