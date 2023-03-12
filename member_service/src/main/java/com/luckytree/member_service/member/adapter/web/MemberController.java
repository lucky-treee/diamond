package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.member.adapter.data.UpdateMemberDto;
import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.domain.MemberProfile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 정보", description = "회원 정보 API 모음")
@RestController
@RequestMapping("v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;

    @Operation(summary = "회원 상세정보 조회")
    @GetMapping
    public ResponseEntity<MemberProfile> getMemberProfile(@RequestParam(name = "nickname") String nickname) {
        MemberProfile memberProfile = memberUseCase.getMemberProfile(nickname);
        return ResponseEntity.ok(memberProfile);
    }

    @Operation(summary = "프로필 정보 변경")
    @PutMapping
    public ResponseEntity<Object> updateMember(@RequestBody @Valid UpdateMemberDto updateMemberDto) {
        memberUseCase.updateMemberRequest(updateMemberDto.getEmail(), updateMemberDto.getNickname(), updateMemberDto.getPhoto());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/delete")
    public ResponseEntity deleteMember(@RequestParam(value = "userId") String email) {
        memberUseCase.deleteMemberRequest(email);
        return ResponseEntity.noContent().build();
    }
}
