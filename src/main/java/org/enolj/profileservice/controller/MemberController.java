package org.enolj.profileservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.enolj.profileservice.dto.GetMemberResponse;
import org.enolj.profileservice.dto.SaveMemberRequest;
import org.enolj.profileservice.dto.SaveMemberResponse;
import org.enolj.profileservice.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<SaveMemberResponse> saveMember(@Valid @RequestBody SaveMemberRequest request) {
        SaveMemberResponse response = memberService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<GetMemberResponse> getMember(@PathVariable Long memberId) {
        GetMemberResponse response = memberService.getMember(memberId);
        return ResponseEntity.ok(response);
    }
}
