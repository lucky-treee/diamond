package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.member.application.port.incoming.GetMemberUseCase;
import com.luckytree.member_service.member.domain.MemberProfile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 조회", description = "회원 조회 API 모음")
@RestController
@RequestMapping("v1/member")
@RequiredArgsConstructor
public class MemberProfileController {

    private final GetMemberUseCase getMemberUseCase;

    @Operation(summary = "회원 상세정보 조회")
    @GetMapping("/get/profile")
    public ResponseEntity<MemberProfile> getMemberProfile(@RequestParam String nickname) {
        MemberProfile memberProfile = getMemberUseCase.getMemberProfile(nickname);
        return new ResponseEntity<>(memberProfile, HttpStatus.OK);
    }

}
