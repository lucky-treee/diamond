package com.luckytree.member.member.adapter.web;

import com.luckytree.member.member.adapter.data.member.*;
import com.luckytree.member.member.application.port.incoming.MemberUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Tag(name = "회원", description = "회원")
@RestController
@RequestMapping("v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;

    @Operation(summary = "회원 조회")
    @GetMapping("{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        MemberResponse memberResponse = memberUseCase.getMember(id);
        return ResponseEntity.ok(memberResponse);
    }

    @Operation(summary = "회원 조회 by 아이디 이메일")
    @GetMapping
    public ResponseEntity<GetMemberResponse> getMemberByIdAndEmail(@RequestParam GetMemberRequest getMemberRequest) {
        GetMemberResponse getMemberResponse = memberUseCase.getMember(getMemberRequest.toDomain());
        return ResponseEntity.ok(getMemberResponse);
    }

    @Operation(summary = "회원 수정")
    @ResponseStatus(NO_CONTENT)
    @PatchMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid UpdateMemberRequest updateMemberRequest) {
        memberUseCase.update(updateMemberRequest.toMemberDetail(id));
    }

    @Operation(summary = "회원 탈퇴")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping
    public void leave() {
        memberUseCase.leave((Long)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Operation(summary = "회원 가입")
    @PostMapping("signup")
    public ResponseEntity<MemberResponse> create(@RequestBody @Valid CreateMemberRequest createMemberRequest) {
        MemberResponse memberResponse = memberUseCase.create(createMemberRequest.toDomain());
        return ResponseEntity.ok(memberResponse);
    }
}
