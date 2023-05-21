package com.luckytree.member.member.adapter.web;

import com.luckytree.member.member.adapter.data.bookmark.BookmarksResponse;
import com.luckytree.member.member.adapter.data.bookmark.CreateBookmarkRequest;
import com.luckytree.member.member.application.port.incoming.BookmarkUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "즐겨찾기", description = "회원의 즐겨찾기")
@RestController
@RequestMapping("v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkUseCase bookmarkUseCase;

    @Operation(summary = "즐겨찾기 등록")
    @PostMapping
    public ResponseEntity<Object> createBookmark(@RequestBody CreateBookmarkRequest createBookmarkRequest) {
        bookmarkUseCase.create(createBookmarkRequest.toDomain());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "즐겨찾기 목록 조회")
    @GetMapping
    public ResponseEntity<BookmarksResponse> getBookmarks() {
        return ResponseEntity.ok(bookmarkUseCase.getBookmarks((Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    @Operation(summary = "즐겨찾기 해제")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteBookmark(@RequestParam(name = "shopId") long shopId) {
        bookmarkUseCase.delete(shopId);
    }
}
