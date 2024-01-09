package com.study.oas.member.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberModifyDto {
    @Getter
    public static class Request {
        private long seqMember;
        private String email;
        private String name;
        private String phoneNumber;
    }
}
