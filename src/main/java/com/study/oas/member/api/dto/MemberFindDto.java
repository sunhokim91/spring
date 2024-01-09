package com.study.oas.member.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberFindDto {
    @Getter
    @Builder
    public static class Response {
        private String name;
        private String phoneNumber;
        private String email;
    }
}
