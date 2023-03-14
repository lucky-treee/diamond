package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.common.annotation.LoginMemberId;
import com.luckytree.member_service.member.adapter.data.ShopDetailDto;
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

import java.util.List;

@Tag(name = "회원 정보", description = "회원 정보 API 모음")
@RestController
@RequestMapping("v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;

    @Operation(summary = "내 프로필 조회")
    @GetMapping
    public ResponseEntity<MemberProfile> getMemberProfile(@LoginMemberId long memberId) {
        MemberProfile memberProfile = memberUseCase.getMemberProfile(memberId);
        return ResponseEntity.ok(memberProfile);
    }

    @Operation(summary = "프로필 수정")
    @PutMapping
    public ResponseEntity<Object> updateMember(@RequestBody @Valid UpdateMemberDto updateMemberDto) {
        memberUseCase.updateMember(updateMemberDto.getEmail(), updateMemberDto.getNickname(), updateMemberDto.getPhoto());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "내 북마크 목록 조회")
    @GetMapping("/member/bookmark")
    public ResponseEntity<List<ShopDetailDto>> getBookmark(@RequestHeader(name = "memberId") long memberId) {
        List<ShopDetailDto> myBookmarks = memberUseCase.getBookMark(memberId);
        return ResponseEntity.ok(myBookmarks);
    }

    @Operation(summary = "즐겨찾기 해제")
    @DeleteMapping
    public ResponseEntity<Object> deleteBookmark(@RequestHeader(name = "memberId") long memberId, @RequestParam(name = "shopId") String shopId) {
        memberUseCase.deleteBookMark(memberId, shopId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 탈퇴")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/leave")
    public void leaveMember(@LoginMemberId long memberId) {
        memberUseCase.deleteMember(memberId);
    }
}
