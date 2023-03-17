package com.luckytree.shop_service.shop.adapter.web;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.shop.adapter.data.*;
import com.luckytree.shop_service.shop.application.port.incoming.RemoveRequestForm;
import com.luckytree.shop_service.shop.application.port.incoming.ShopUseCase;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.shop.adapter.data.ShopDetail;
import com.luckytree.shop_service.shop.adapter.data.ShopSummary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Shop API", description = "프론트엔드 사용")
@RestController
@RequestMapping("/v1/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopUseCase shopUseCase;

    @Operation(summary = "샵 등록요청 API")
    @PostMapping("/shop")
    public ResponseEntity<Object> createShop(@RequestBody @Valid CreateShopDto createShopDto) {
        shopUseCase.createShop(createShopDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "특정 카테고리의 샵 전체 검색")
    @GetMapping("/{category}")
    public ResponseEntity<List<ShopSummary>> findShopsByCategory(@PathVariable Category category) {
        List<ShopSummary> shopSummaryList = shopUseCase.findShopsByCategory(category);
        return ResponseEntity.ok(shopSummaryList);
    }

    @Operation(summary = "범위 내 샵 전체 검색")
    @GetMapping
    public ResponseEntity<List<ShopSummary>> findShopsByLatAndLng(ShopLatLngRequest shopLatLngRequest) {
        List<ShopSummary> shopSummary = shopUseCase.findShopsByLatAndLng(shopLatLngRequest.getMaxLat(), shopLatLngRequest.getMinLat(), shopLatLngRequest.getMaxLng(), shopLatLngRequest.getMinLng());
        return ResponseEntity.ok(shopSummary);
    }

    @Operation(summary = "샵이름과 주소로 상세조회")
    @GetMapping("/shop")
    public ResponseEntity<ShopDetail> findShopByNameAndAddress(@RequestParam(name = "name") String name, @RequestParam(name = "address") String address) {
        ShopDetail shopDetail = shopUseCase.findShopByNameAndAddress(name, address);
        return ResponseEntity.ok(shopDetail);
    }

    @Operation(summary = "해쉬태그로 샵 목록 조회")
    @GetMapping("/{hashtag}")
    public ResponseEntity<List<ShopSummary>> findShopsByHashtag(@PathVariable Hashtag hashtag) {
        List<ShopSummary> shopSummaryList = shopUseCase.findShopsByHashtag(hashtag);
        return ResponseEntity.ok(shopSummaryList);
    }

    @Operation(summary = "샵 삭제요청 API")
    @DeleteMapping("/shop")
    public ResponseEntity<Object> deleteShop(@RequestBody @Valid RemoveRequestForm removeRequestForm) {
        shopUseCase.deleteShop(removeRequestForm);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 등록")
    @PostMapping("/shop/review")
    public ResponseEntity<Object> createShopReview(@RequestBody @Valid ReviewRequest reviewRequest){

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 수정")
    @PutMapping("/shop/review")
    public ResponseEntity<Object> updateReview(@RequestBody @Valid UpdateReviewDto updateReviewDto) {

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 조회")
    @GetMapping("/shop/review")
    public ResponseEntity<Object> findShopReviewByMemberIdOrShopId(@RequestHeader(name= "memberId") long memberId, @RequestParam(name = "shopId") String shopId) {

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 삭제")
    @DeleteMapping("/shop/review")
    ResponseEntity<Object> deleteShopReview(@RequestHeader(name= "memberId") long memberId, @RequestParam(name = "shopId") String shopId) {

        return ResponseEntity.ok().build();
    }
}
