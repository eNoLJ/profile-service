package org.enolj.profileservice.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.enolj.profileservice.entity.Member;

@Getter
@Builder(access = AccessLevel.PROTECTED)
public class SaveMemberResponse {

    private final Long id;
    private final String name;
    private final Integer age;
    private final String mbti;

    public static SaveMemberResponse from(Member member) {
        return SaveMemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .mbti(member.getMbti())
                .build();
    }
}
