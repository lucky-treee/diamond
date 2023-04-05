package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.common.annotation.LoginMemberId;
import com.luckytree.member_service.member.adapter.data.UpdateMemberRequest;
import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.adapter.data.MemberResponse;
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
    public ResponseEntity<MemberResponse> getMember(@LoginMemberId long memberId) {
        MemberResponse memberResponse = memberUseCase.getMember(memberId);
        return ResponseEntity.ok(memberResponse);
    }

    @Operation(summary = "프로필 수정(로그인)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateMember(@LoginMemberId long memberId, @RequestBody @Valid UpdateMemberRequest updateMemberRequest) {
        memberUseCase.update(memberId, updateMemberRequest.getNickname(), updateMemberRequest.getPhoto());
    }

    @Operation(summary = "회원 탈퇴(로그인)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/leave")
    public void leaveMember(@LoginMemberId long memberId) {
        memberUseCase.leave(memberId);
    }
}
