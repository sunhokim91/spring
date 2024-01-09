package com.study.oas.member.infrastructure.jpa;

import com.study.oas.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Member findMemberBySeqMember(Long seqMember);
}
