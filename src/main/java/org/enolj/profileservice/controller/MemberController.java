package org.enolj.profileservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.enolj.profileservice.dto.*;
import org.enolj.profileservice.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<SaveMemberResponse> saveMember(@Valid @RequestBody SaveMemberRequest request) {
        log.info("[API - LOG] 회원 저장 컨트롤러");
        SaveMemberResponse response = memberService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<GetMemberResponse> getMember(@PathVariable Long memberId) {
        log.info("[API - LOG] 회원 조회 컨트롤러");
        GetMemberResponse response = memberService.getMember(memberId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{memberId}/profile-image")
    public ResponseEntity<String> profileUpload(
            @PathVariable Long memberId,
            @RequestParam("file") MultipartFile file
    ) {
        log.info("[API - LOG] 프로필 업로드 컨트롤러");
        memberService.profileUpload(memberId, file);
        return ResponseEntity.ok("프로필 이미지 업로드 완료");
    }

    @GetMapping("/{memberId}/profile-image")
    public ResponseEntity<ProfileDownloadResponse> profileDownload(@PathVariable Long memberId) {
        log.info("[API - LOG] 프로필 다운로드 컨트롤러");
        ProfileDownloadResponse response = memberService.profileDownload(memberId);
        return ResponseEntity.ok(response);
    }
}
