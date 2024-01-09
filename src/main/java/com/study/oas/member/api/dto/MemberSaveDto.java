package com.study.oas.member.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSaveDto {
    @Getter
    public static class Request {
        private String email;
        private String name;
        private String phoneNumber;
    }
}
