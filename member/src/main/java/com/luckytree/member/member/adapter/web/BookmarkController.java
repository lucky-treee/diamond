package com.luckytree.member.member.adapter.web;

import com.luckytree.member.member.adapter.data.bookmark.BookmarksResponse;
import com.luckytree.member.member.adapter.data.bookmark.CreateBookmarkRequest;
import com.luckytree.member.member.application.port.incoming.BookmarkUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.jwt.AuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Tag(name = "즐겨찾기", description = "회원의 즐겨찾기")
@RestController
@RequestMapping("v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkUseCase bookmarkUseCase;

    @Operation(summary = "즐겨찾기 등록")
    @ResponseStatus(CREATED)
    @PostMapping
    public void createBookmark(@RequestBody CreateBookmarkRequest createBookmarkRequest) {
        bookmarkUseCase.create(createBookmarkRequest.toDomain(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())));
    }

    @Operation(summary = "즐겨찾기 목록 조회")
    @GetMapping
    public ResponseEntity<BookmarksResponse> getBookmarks() {
        return ResponseEntity.ok(bookmarkUseCase.getBookmarks(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())));
    }

    @Operation(summary = "즐겨찾기 해제")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping
    public void deleteBookmark(@RequestParam(name = "shopId") Long shopId) {
        bookmarkUseCase.delete(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()), shopId);
    }
}
