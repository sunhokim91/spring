package com.study.oas.member.api;

import com.study.oas.member.api.dto.MemberFindDto;
import com.study.oas.member.api.dto.MemberModifyDto;
import com.study.oas.member.api.dto.MemberSaveDto;
import com.study.oas.member.application.MemberService;
import com.study.oas.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{seqMember}")
    public MemberFindDto.Response find(@PathVariable Long seqMember) {
        Member member = memberService.find(seqMember);

        return MemberFindDto.Response
                .builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    @PostMapping
    public void save(@RequestBody MemberSaveDto.Request request) {
        Member member = Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();

        memberService.save(member);
    }

    @DeleteMapping("/{seqMember}")
    public void remove(@PathVariable Long seqMember) {
        memberService.remove(seqMember);
    }

    @PutMapping
    public void modify(@RequestBody MemberModifyDto.Request request) {
        Member member = Member.builder()
                .seqMember(request.getSeqMember())
                .phoneNumber(request.getPhoneNumber())
                .name(request.getName())
                .email(request.getEmail())
                .build();

        memberService.modify(member);
    }
}
