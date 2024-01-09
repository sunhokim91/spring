package com.study.oas.member.domain.repository;

import com.study.oas.member.domain.Member;

public interface MemberRepository {
    Member findMember(Long seqMember);
    void save(Member member);
    void remove(Member member);
    void modify(Member member);
}
