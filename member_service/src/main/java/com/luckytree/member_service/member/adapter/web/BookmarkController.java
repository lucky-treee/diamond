package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.common.annotation.LoginMemberId;
import com.luckytree.member_service.member.adapter.data.BookmarksResponse;
import com.luckytree.member_service.member.application.port.incoming.BookmarkUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "즐겨찾기", description = "회원의 즐겨찾기")
@RestController
@RequestMapping("/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkUseCase bookmarkUseCase;

    @Operation(summary = "북마크 목록 조회(로그인)")
    @GetMapping
    public ResponseEntity<BookmarksResponse> getBookmarks(@LoginMemberId long memberId) {
        return ResponseEntity.ok(bookmarkUseCase.getBookmarks(memberId));
    }

    @Operation(summary = "즐겨찾기 해제(로그인)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteBookmark(@LoginMemberId long memberId, @RequestParam(name = "shopId") long shopId) {
        bookmarkUseCase.delete(memberId, shopId);
    }
}
