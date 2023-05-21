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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원", description = "회원")
@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;

    @Operation(summary = "프로필 조회")
    @GetMapping
    public ResponseEntity<MemberResponse> getMember() {
        MemberResponse memberResponse = memberUseCase.getMember((Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok(memberResponse);
    }

    @Operation(summary = "프로필 수정")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateMember(@RequestBody @Valid UpdateMemberRequest updateMemberRequest) {
        memberUseCase.update(updateMemberRequest, (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Operation(summary = "회원 탈퇴")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/leave")
    public void leaveMember() {
        memberUseCase.leave((Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
