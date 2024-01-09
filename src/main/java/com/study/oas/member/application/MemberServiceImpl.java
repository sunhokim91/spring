package com.study.oas.member.application;

import com.study.oas.member.domain.Member;
import com.study.oas.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public Member find(Long seqMobile) {
        return memberRepository.findMember(seqMobile);
    }

    @Override
    @Transactional
    public void save(Member member) {
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void remove(Long seqMember) {
        memberRepository.remove(Member.builder()
                .seqMember(seqMember)
                .build());
    }

    @Override
    @Transactional
    public void modify(Member member) {
        memberRepository.save(member);
    }
}
