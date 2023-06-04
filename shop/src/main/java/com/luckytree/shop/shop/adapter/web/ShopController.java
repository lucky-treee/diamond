package com.luckytree.shop.shop.adapter.web;

import com.luckytree.shop.shop.adapter.data.bookmark.MyBookmarksResponse;
import com.luckytree.shop.shop.adapter.data.shop.*;
import com.luckytree.shop.shop.application.port.incoming.ShopUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Shop API", description = "샵")
@RestController
@RequestMapping("v1/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopUseCase shopUseCase;

    @Operation(summary = "샵 등록요청 API")
    @PostMapping
    public ResponseEntity<Object> createShop(@RequestBody @Valid CreateShopRequest createShopRequest) {
        shopUseCase.createShop(createShopRequest.toDomain());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "특정 카테고리의 샵 전체 검색")
    @GetMapping("/{category}")
    public ResponseEntity<List<ShopSummaryResponse>> findShopsByCategory(@PathVariable ShopCategory category) {
        List<ShopSummaryResponse> shopSummaryResponseList = shopUseCase.findShopsByCategory(category);
        return ResponseEntity.ok(shopSummaryResponseList);
    }

    @Operation(summary = "범위 내 샵 전체 검색")
    @GetMapping
    public ResponseEntity<List<ShopSummaryResponse>> findShopsByLatAndLng(SearchShopRequest searchShopRequest) {
        List<ShopSummaryResponse> shopSummaryResponse = shopUseCase.findShopsByLatAndLng(searchShopRequest);
        return ResponseEntity.ok(shopSummaryResponse);
    }

    @Operation(summary = "샵아이디로 상세조회")
    @GetMapping("/shop")
    public ResponseEntity<ShopDetailResponse> findShopByShopId(@RequestParam(name = "shopId") Long shopId) {
        ShopDetailResponse shopDetailResponse = shopUseCase.findShopById(shopId);
        return ResponseEntity.ok(shopDetailResponse);
    }

    @Operation(summary = "해쉬태그로 샵 목록 조회")
    @GetMapping("/{hashtag}")
    public ResponseEntity<List<ShopSummaryResponse>> findShopsByHashtag(@PathVariable ShopHashtag hashtag) {
        List<ShopSummaryResponse> shopSummaryResponseList = shopUseCase.findShopsByHashtag(hashtag);
        return ResponseEntity.ok(shopSummaryResponseList);
    }

    @Operation(summary = "샵 삭제요청 API")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteShop(@RequestBody @Valid DeleteShopRequest deleteShopRequest) {
        shopUseCase.deleteShop(deleteShopRequest.toDomain());
    }

    @PostMapping("/bookmarks")
    public MyBookmarksResponse findShopsByIds(@RequestBody ShopFeignRequest shopFeignRequest) {
        return shopUseCase.findMyBookmarksResponseByIds(shopFeignRequest.getShopIds());
    }
}