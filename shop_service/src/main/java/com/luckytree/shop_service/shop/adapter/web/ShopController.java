package com.luckytree.shop_service.shop.adapter.web;

import com.luckytree.shop_service.common.annotation.LoginMemberId;
import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.shop.adapter.data.*;
import com.luckytree.shop_service.shop.adapter.data.ShopDeleteDto;
import com.luckytree.shop_service.shop.application.port.incoming.ShopUseCase;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.shop.adapter.data.ShopDetailDto;
import com.luckytree.shop_service.shop.adapter.data.ShopSummaryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<ShopSummaryDto>> findShopsByCategory(@PathVariable Category category) {
        List<ShopSummaryDto> shopSummaryDtoList = shopUseCase.findShopsByCategory(category);
        return ResponseEntity.ok(shopSummaryDtoList);
    }

    @Operation(summary = "범위 내 샵 전체 검색")
    @GetMapping
    public ResponseEntity<List<ShopSummaryDto>> findShopsByLatAndLng(ShopSearchDto shopSearchDto) {
        List<ShopSummaryDto> shopSummaryDto = shopUseCase.findShopsByLatAndLng(shopSearchDto.getMaxLat(), shopSearchDto.getMinLat(), shopSearchDto.getMaxLng(), shopSearchDto.getMinLng());
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
    public ResponseEntity<List<ShopSummaryDto>> findShopsByHashtag(@PathVariable Hashtag hashtag) {
        List<ShopSummaryDto> shopSummaryDtoList = shopUseCase.findShopsByHashtag(hashtag);
        return ResponseEntity.ok(shopSummaryDtoList);
    }

    @Operation(summary = "샵 삭제요청 API(로그인)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/shop")
    public void deleteShop(@LoginMemberId long memberId, @RequestBody @Valid ShopDeleteDto shopDeleteDto) {
        shopUseCase.deleteShop(shopDeleteDto.getName(), shopDeleteDto.getAddress(), shopDeleteDto.getComment());
    }

    @Operation(summary = "샵 리뷰 등록(로그인)")
    @PostMapping("/shop/review")
    public ResponseEntity<Object> createShopReview(@LoginMemberId long memberId, @RequestBody @Valid CreateReviewDto createReviewDto){

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 수정(로그인)")
    @PutMapping("/shop/review")
    public ResponseEntity<Object> updateReview(@LoginMemberId long memberId, @RequestBody @Valid UpdateReviewDto updateReviewDto) {

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 조회")
    @GetMapping("/shop/review")
    public ResponseEntity<Object> findShopReviewByMemberIdOrShopId(@LoginMemberId long memberId, @RequestParam(name = "shopId") String shopId) {

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 삭제(로그인)")
    @DeleteMapping("/shop/review")
    ResponseEntity<Object> deleteShopReview(@LoginMemberId long memberId, @RequestParam(name = "shopId") String shopId) {

        return ResponseEntity.ok().build();
    }

}
