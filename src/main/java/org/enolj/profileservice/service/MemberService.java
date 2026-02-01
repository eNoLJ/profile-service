package org.enolj.profileservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.enolj.profileservice.dto.GetMemberResponse;
import org.enolj.profileservice.dto.SaveMemberRequest;
import org.enolj.profileservice.dto.SaveMemberResponse;
import org.enolj.profileservice.entity.Member;
import org.enolj.profileservice.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public SaveMemberResponse save(SaveMemberRequest request) {
        Member member = Member.from(request);
        memberRepository.save(member);
        return  SaveMemberResponse.from(member);
    }

    @Transactional(readOnly = true)
    public GetMemberResponse getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> {
                    log.error("Member 조회 실패 - memberId={}", memberId);
                    return new IllegalArgumentException("해당 멤버를 찾을 수 없습니다.");
                }
        );
        return GetMemberResponse.from(member);
    }
}
