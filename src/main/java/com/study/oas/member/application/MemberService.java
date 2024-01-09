package com.study.oas.member.application;

import com.study.oas.member.domain.Member;

public interface MemberService {
    Member find(Long seqMobile);
    void save(Member member);
    void remove(Long seqMobile);
    void modify(Member member);
}
