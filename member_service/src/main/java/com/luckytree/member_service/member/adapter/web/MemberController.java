package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.member.adapter.data.ShopDetailDto;
import com.luckytree.member_service.member.adapter.data.UpdateMemberDto;
import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.domain.MemberProfile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "회원 정보", description = "회원 정보 API 모음")
@RestController
@RequestMapping("v1/members")
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

    @Operation(summary = "내 북마크 목록 조회")
    @GetMapping("/member/bookmark")
    public ResponseEntity<List<ShopDetailDto>> getFavorite(@RequestHeader(name = "userId") String userId) {
        List<ShopDetailDto> myBookmarks = memberUseCase.getBookmarkRequest(userId);
        return ResponseEntity.ok(myBookmarks);
    }
}
