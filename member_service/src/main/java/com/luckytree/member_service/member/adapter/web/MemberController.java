package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.member.adapter.data.UpdateMemberDto;
import com.luckytree.member_service.member.adapter.data.WithdrawalMemberDto;
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
    @GetMapping("/get/profile")
    public ResponseEntity<MemberProfile> getMemberProfile(@RequestParam String nickname) {
        MemberProfile memberProfile = memberUseCase.getMemberProfile(nickname);
        return new ResponseEntity<>(memberProfile, HttpStatus.OK);
    }

    @Operation(summary = "프로필 정보 변경")
    @PutMapping("/update/profile")
    public ResponseEntity<Object> updateMember(@RequestBody @Valid UpdateMemberDto updateMemberDto) {
        memberUseCase.updateMemberRequest(updateMemberDto.getEmail(), updateMemberDto.getNickname(), updateMemberDto.getPhoto());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "회원 탈퇴")
    @PutMapping("/withdrawal/profile")
    public ResponseEntity<Object> withdrawalMember(@RequestBody @Valid WithdrawalMemberDto withdrawalMemberDto) {
        memberUseCase.withdrawalMemberRequest(withdrawalMemberDto.getEmail(), withdrawalMemberDto.getStatus());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
