package com.luckytree.member.member.adapter.web.internal;

import com.luckytree.member.member.adapter.data.CreateBookmarkRequest;
import com.luckytree.member.member.application.port.incoming.BookmarkUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Internal API", description = "즐겨찾기")
@RestController
@RequestMapping("/v1/bookmarks")
@RequiredArgsConstructor
public class internalBookmarkController {

    private final BookmarkUseCase bookmarkUseCase;

    @PostMapping
    public void createBookmark(@RequestBody CreateBookmarkRequest createBookmarkRequest) {
        bookmarkUseCase.create(createBookmarkRequest);
    }

    @DeleteMapping("/{memberId}")
    public void deleteBookmark(@PathVariable long memberId, @RequestParam(name = "shopId") long shopId) {
        bookmarkUseCase.delete(memberId, shopId);
    }
} 