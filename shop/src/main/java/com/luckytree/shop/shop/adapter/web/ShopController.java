package com.luckytree.shop.shop.adapter.web;

import com.luckytree.shop.shop.adapter.data.review.CreateReviewDto;
import com.luckytree.shop.shop.adapter.data.review.UpdateReviewDto;
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

@Tag(name = "Shop API", description = "프론트엔드 사용")
@RestController
@RequestMapping("/v1/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopUseCase shopUseCase;

    @Operation(summary = "샵 등록요청 API(로그인)")
    @PostMapping("/shop")
    public ResponseEntity<Object> createShop(@RequestBody @Valid CreateShopDto createShopDto) {
        shopUseCase.createShop(createShopDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "특정 카테고리의 샵 전체 검색")
    @GetMapping("/{category}")
    public ResponseEntity<List<ShopSummaryDto>> findShopsByCategory(@PathVariable ShopCategory category) {
        List<ShopSummaryDto> shopSummaryDtoList = shopUseCase.findShopsByCategory(category);
        return ResponseEntity.ok(shopSummaryDtoList);
    }

    @Operation(summary = "범위 내 샵 전체 검색")
    @GetMapping
    public ResponseEntity<List<ShopSummaryDto>> findShopsByLatAndLng(ShopSearchDto shopSearchDto) {
        List<ShopSummaryDto> shopSummaryDto = shopUseCase.findShopsByLatAndLng(shopSearchDto);
        return ResponseEntity.ok(shopSummaryDto);
    }

    @Operation(summary = "샵아이디로 상세조회")
    @GetMapping("/shop")
    public ResponseEntity<ShopDetailDto> findShopByShopId(@RequestParam(name = "shopId") long shopId) {
        ShopDetailDto shopDetailDto = shopUseCase.findShopById(shopId);
        return ResponseEntity.ok(shopDetailDto);
    }

    @Operation(summary = "해쉬태그로 샵 목록 조회")
    @GetMapping("/{hashtag}")
    public ResponseEntity<List<ShopSummaryDto>> findShopsByHashtag(@PathVariable ShopHashtag hashtag) {
        List<ShopSummaryDto> shopSummaryDtoList = shopUseCase.findShopsByHashtag(hashtag);
        return ResponseEntity.ok(shopSummaryDtoList);
    }

    @Operation(summary = "샵 삭제요청 API(로그인)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/shop")
    public void deleteShop(@RequestHeader("Authorization") String authorization, @RequestBody @Valid ShopDeleteDto shopDeleteDto) {
        shopUseCase.deleteShop(authorization, shopDeleteDto);
    }

    @Operation(summary = "샵 리뷰 등록(로그인)")
    @PostMapping("/shop/review")
    public ResponseEntity<Object> createShopReview(@RequestHeader("Authorization") String authorization, @RequestBody @Valid CreateReviewDto createReviewDto) {

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 수정(로그인)")
    @PutMapping("/shop/review")
    public ResponseEntity<Object> updateReview(@RequestHeader("Authorization") String authorization, @RequestBody @Valid UpdateReviewDto updateReviewDto) {

        return ResponseEntity.ok().build();
    }
}
