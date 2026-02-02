package org.enolj.profileservice.service;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.enolj.profileservice.dto.*;
import org.enolj.profileservice.entity.Member;
import org.enolj.profileservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private static final Duration PRESIGNED_URL_EXPIRATION = Duration.ofDays(7);

    private final S3Template s3Template;

    private final MemberRepository memberRepository;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public SaveMemberResponse save(SaveMemberRequest request) {
        Member member = Member.from(request);
        memberRepository.save(member);
        return  SaveMemberResponse.from(member);
    }

    @Transactional(readOnly = true)
    public GetMemberResponse getMember(Long memberId) {
        Member member = findById(memberId);
        return GetMemberResponse.from(member);
    }

    @Transactional
    public void profileUpload(Long memberId, MultipartFile file) {
        try {
            String profile = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            s3Template.upload(bucket, profile, file.getInputStream());
            // 이미지 업로드 성공 시 멤버 조회
            Member member = findById(memberId);
            member.profileUpload(profile);
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }

    @Transactional(readOnly = true)
    public ProfileDownloadResponse profileDownload(Long memberId) {
        Member member = findById(memberId);
        URL url = s3Template.createSignedGetURL(bucket, member.getProfile(), PRESIGNED_URL_EXPIRATION);
        return ProfileDownloadResponse.from(url.toString());
    }

    private Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> {
                    log.error("Member 조회 실패 - memberId={}", memberId);
                    return new IllegalArgumentException("해당 멤버를 찾을 수 없습니다.");
                }
        );
    }
}
