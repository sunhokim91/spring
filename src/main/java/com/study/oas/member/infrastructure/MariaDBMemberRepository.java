package com.study.oas.member.infrastructure;

import com.study.oas.member.domain.Member;
import com.study.oas.member.domain.repository.MemberRepository;
import com.study.oas.member.infrastructure.jpa.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MariaDBMemberRepository implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member findMember(Long seqMember) {
        return memberJpaRepository.findMemberBySeqMember(seqMember);
    }

    @Override
    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    @Override
    public void remove(Member member) {
        memberJpaRepository.delete(member);
    }

    @Override
    public void modify(Member member) {
        memberJpaRepository.save(member);
    }
}
