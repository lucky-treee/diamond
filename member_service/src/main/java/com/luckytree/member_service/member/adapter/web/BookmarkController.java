package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.common.annotation.LoginMemberId;
import com.luckytree.member_service.member.adapter.data.MyBookmarksDto;
import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "즐겨찾기", description = "회원의 즐겨찾기")
@RestController
@RequestMapping("v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final MemberUseCase memberUseCase;

    @Operation(summary = "내 북마크 목록 조회")
    @GetMapping
    public ResponseEntity<MyBookmarksDto> findMyBookmarks(@LoginMemberId long memberId) {
        return ResponseEntity.ok(memberUseCase.findMyBookmarks(memberId));
    }

    @Operation(summary = "즐겨찾기 해제")
    @DeleteMapping
    public ResponseEntity<Object> deleteBookmark(@RequestHeader(name = "memberId") long memberId, @RequestParam(name = "shopId") String shopId) {
        memberUseCase.deleteBookMark(memberId, shopId);
        return ResponseEntity.ok().build();
    }
}
