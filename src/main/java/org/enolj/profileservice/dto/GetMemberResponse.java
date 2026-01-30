package org.enolj.profileservice.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.enolj.profileservice.entity.Member;

@Getter
@Builder(access = AccessLevel.PROTECTED)
public class GetMemberResponse {

    private final Long id;
    private final String name;
    private final Integer age;
    private final String MBTI;

    public static GetMemberResponse from(Member member) {
        return GetMemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .MBTI(member.getMBTI())
                .build();
    }
}
