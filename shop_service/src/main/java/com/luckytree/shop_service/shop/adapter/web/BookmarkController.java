package com.luckytree.shop_service.shop.adapter.web;

import com.luckytree.shop_service.shop.adapter.data.BookmarkRequest;
import com.luckytree.shop_service.shop.application.port.incoming.BookmarkUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "즐겨찾기", description = "샵 즐겨찾기")
@RestController
@RequestMapping("/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkUseCase bookmarkUseCase;

    @Operation(summary = "즐겨찾기 추가")
    @PostMapping
    public ResponseEntity<Object> createBookmark(@RequestBody BookmarkRequest bookmarkRequest) {
        bookmarkUseCase.createBookmark(bookmarkRequest.getMemberId(), bookmarkRequest.getShopId());
        return ResponseEntity.ok().build();
    }
}