package com.luckytree.member.member.adapter.web;

import com.luckytree.member.member.adapter.data.MemberResponse;
import com.luckytree.member.member.adapter.data.UpdateMemberRequest;
import com.luckytree.member.member.application.port.incoming.MemberUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 정보", description = "회원 정보 API 모음")
@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;

    @Operation(summary = "프로필 조회(로그인)")
    @GetMapping
    public ResponseEntity<MemberResponse> getMember(@RequestHeader("Authorization") String authorization) {
        MemberResponse memberResponse = memberUseCase.getMember(authorization, 0L);
        return ResponseEntity.ok(memberResponse);
    }

    @Operation(summary = "프로필 수정(로그인)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateMember(@RequestHeader("Authorization") String authorization, @RequestBody @Valid UpdateMemberRequest updateMemberRequest) {
        memberUseCase.update(authorization, updateMemberRequest, 0L);
    }

    @Operation(summary = "회원 탈퇴(로그인)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/leave")
    public void leaveMember(@RequestHeader("Authorization") String authorization) {
        memberUseCase.leave(authorization, 0L);
    }
}