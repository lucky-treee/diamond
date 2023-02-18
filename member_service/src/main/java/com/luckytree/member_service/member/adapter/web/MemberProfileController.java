package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.member.application.port.incoming.GetMemberUseCase;
import com.luckytree.member_service.member.domain.MemberInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 조회", description = "회원 조회 API 모음")
@RestController
@RequestMapping("/member/get")
@RequiredArgsConstructor
public class MemberProfileController {

    private final GetMemberUseCase getMemberUseCase;

    @Operation(summary = "회원 상세정보 조회")
    @GetMapping("/Info/{nickname}/{email}")
    public ResponseEntity<MemberInfo> getMemberInfo(@PathVariable("nickname") String nickname, @PathVariable("email") String email) {
        MemberInfo memberInfo = getMemberUseCase.getMemberInfo(nickname, email);
        return new ResponseEntity<>(memberInfo, HttpStatus.OK);
    }

}
