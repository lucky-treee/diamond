package com.luckytree.member_service.member.adapter.web.internal;

import com.luckytree.member_service.member.adapter.data.CreateBookmarkRequest;
import com.luckytree.member_service.member.application.port.incoming.BookmarkUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Internal API", description = "즐겨찾기")
@RestController
@RequestMapping("/v1/internal/bookmarks")
@RequiredArgsConstructor
public class internalBookmarkController {

    private final BookmarkUseCase bookmarkUseCase;

    @PostMapping
    public void createBookmark(@RequestBody CreateBookmarkRequest createBookmarkRequest) {
        bookmarkUseCase.create(createBookmarkRequest);
    }
} 