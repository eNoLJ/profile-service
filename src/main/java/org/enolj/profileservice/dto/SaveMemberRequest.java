package org.enolj.profileservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SaveMemberRequest {

    @NotBlank
    private String name;
    @NotNull
    private Integer age;
    @NotBlank
    @Pattern(regexp = "^(INTJ|INTP|ENTJ|ENTP|INFJ|INFP|ENFJ|ENFP|ISTJ|ISFJ|ESTJ|ESFJ|ISTP|ISFP|ESTP|ESFP)$")
    private String mbti;
}
