package com.luckytree.shop_service.shop.adapter.web;

import com.luckytree.shop_service.common.annotation.LoginMemberId;
import com.luckytree.shop_service.shop.application.port.incoming.ShopUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "즐겨찾기", description = "샵 즐겨찾기")
@RestController
@RequestMapping("/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final ShopUseCase shopUseCase;

    @Operation(summary = "즐겨찾기 추가(로그인)")
    @PostMapping("/{shopId}")
    public ResponseEntity<Object> createBookmark(@LoginMemberId long memberId, @PathVariable long shopId) {
        shopUseCase.createBookmark(memberId, shopId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 북마크 해제(로그인)")
    @DeleteMapping("/bookmark/{memberId}")
    ResponseEntity<Object> deleteBookmark(@PathVariable@LoginMemberId long memberId, @RequestParam(name = "shopId") long shopId) {
        shopUseCase.deleteBookmark(memberId, shopId);
        return ResponseEntity.noContent().build();
    }
}