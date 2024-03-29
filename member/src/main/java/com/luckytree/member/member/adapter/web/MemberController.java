package com.luckytree.member.member.adapter.web;

import com.luckytree.member.member.adapter.data.member.*;
import com.luckytree.member.member.application.port.incoming.MemberUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.jwt.AuthenticationToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Tag(name = "회원", description = "회원")
@RestController
@RequestMapping("v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;

    @Operation(summary = "회원 정보 조회")
    @GetMapping("{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        MemberResponse memberResponse = memberUseCase.getMember(id);
        return ResponseEntity.ok(memberResponse);
    }

    @Operation(summary = "내 정보 조회")
    @GetMapping("my")
    public ResponseEntity<MemberResponse> getMyMember() {
        MemberResponse memberResponse = memberUseCase.getMember(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()));
        return ResponseEntity.ok(memberResponse);
    }

    @Operation(summary = "회원 조회 by 아이디 이메일")
    @GetMapping
    public ResponseEntity<GetMemberResponse> getMemberByIdAndEmail(GetMemberRequest getMemberRequest) {
        GetMemberResponse getMemberResponse = memberUseCase.getMember(getMemberRequest.toDomain());
        return ResponseEntity.ok(getMemberResponse);
    }

    @Operation(summary = "회원 수정")
    @ResponseStatus(NO_CONTENT)
    @PatchMapping
    public void update(@RequestBody @Valid UpdateMemberRequest updateMemberRequest) {
        memberUseCase.update(updateMemberRequest.toMemberDetail(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())));
    }

    @Operation(summary = "회원 탈퇴")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping
    public void leave() {
        memberUseCase.leave(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()));
    }

    @Operation(summary = "회원 가입")
    @PostMapping("signup")
    public ResponseEntity<MemberResponse> create(@RequestBody @Valid CreateMemberRequest createMemberRequest) {
        MemberResponse memberResponse = memberUseCase.create(createMemberRequest.toDomain());
        return ResponseEntity.ok(memberResponse);
    }
}
