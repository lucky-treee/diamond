package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.common.dto.ResultResponse;
import com.luckytree.member_service.member.application.port.incoming.GetMemberUseCase;
import com.luckytree.member_service.member.application.port.incoming.MemberProfileUseCase;
import com.luckytree.member_service.member.domain.MemberProfile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 정보", description = "회원 정보 API 모음")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberProfileController {

    private final GetMemberUseCase getMemberUseCase;
    private final MemberProfileUseCase memberInfoUseCase;

    @Operation(summary = "회원 상세정보 조회")
    @GetMapping("/get/{nickname}/{email}")
    public ResponseEntity<MemberProfile> getMemberProfile(@PathVariable("nickname") String nickname, @PathVariable("email") String email) {
        MemberProfile memberProfile = getMemberUseCase.getMemberProfile(nickname, email);
        return new ResponseEntity<>(memberProfile, HttpStatus.OK);
    }

    @Operation(summary = "닉네임 변경")
    @PostMapping("/update/nickname/{email}/{newNickname}")
    public ResultResponse updateNickname(@PathVariable("email") String email, @PathVariable("newNickname") String newNickname) {
        memberInfoUseCase.updateNicknameRequest(email, newNickname);
        return new ResultResponse<>(HttpStatus.OK);
    }
}
