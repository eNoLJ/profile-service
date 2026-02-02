package org.enolj.profileservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.enolj.profileservice.dto.SaveMemberRequest;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String mbti;

    public static Member from(SaveMemberRequest request) {
        return Member.builder()
                .name(request.getName())
                .age(request.getAge())
                .mbti(request.getMbti())
                .build();
    }
}

