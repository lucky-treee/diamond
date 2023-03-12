package com.luckytree.shop_service.shop.adapter.web;

import com.luckytree.shop_service.shop.adapter.data.ReviewRequest;
import com.luckytree.shop_service.shop.adapter.data.ShopLatLngRequest;
import com.luckytree.shop_service.shop.adapter.data.ShopRequest;
import com.luckytree.shop_service.shop.adapter.data.UpdateReviewDto;
import com.luckytree.shop_service.shop.application.port.incoming.RemoveRequestForm;
import com.luckytree.shop_service.shop.application.port.incoming.ShopUseCase;
import com.luckytree.shop_service.shop.domain.Hashtag;
import com.luckytree.shop_service.shop.domain.ShopDetail;
import com.luckytree.shop_service.shop.domain.ShopSummary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "샵", description = "샵 전체 API 모음")
@RestController
@RequestMapping("/v1/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopUseCase shopUseCase;

    @Operation(summary = "샵 등록요청 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "샵 등록 완료",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShopRequest.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @PostMapping("/shop")
    public ResponseEntity<Object> createShop(@RequestBody @Valid ShopRequest shopRequest) {
        shopUseCase.createShop(shopRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "특정 카테고리의 샵 전체 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "샵 조회 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShopSummary.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<ShopSummary>> getShopsByCategory(@RequestParam(name = "category") String category) {
        List<ShopSummary> shopSummaryList = shopUseCase.findShopsByCategory(category);
        return ResponseEntity.ok(shopSummaryList);
    }

    @Operation(summary = "범위 내 샵 전체 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "샵 조회 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShopSummary.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<ShopSummary>> getShopsByLatAndLng(ShopLatLngRequest shopLatLngRequest) {
        List<ShopSummary> shopSummary = shopUseCase.findShopsByLatAndLng(shopLatLngRequest.getMaxLat(), shopLatLngRequest.getMinLat(), shopLatLngRequest.getMaxLng(), shopLatLngRequest.getMinLng());
        return ResponseEntity.ok(shopSummary);
    }

    @Operation(summary = "샵이름과 주소로 상세조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "샵 조회 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShopDetail.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @GetMapping("/shop")
    public ResponseEntity<ShopDetail> getShopByNameAndAddress(@RequestParam(name = "name") String name, @RequestParam(name = "address") String address) {
        ShopDetail shopDetail = shopUseCase.findShopByNameAndAddress(name, address);
        return ResponseEntity.ok(shopDetail);
    }

    @Operation(summary = "해쉬태그로 샵 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "샵 조회 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShopSummary.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<ShopSummary>> getShopsByHashtag(@RequestParam(name = "hashtag") Hashtag hashtag) {
        List<ShopSummary> shopSummaryList = shopUseCase.findShopsByHashtag(hashtag);
        return ResponseEntity.ok(shopSummaryList);
    }

    @Operation(summary = "샵 삭제요청 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "샵 삭제요청 완료",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @DeleteMapping("/shop")
    public ResponseEntity<Object> removeShopRequest(@RequestBody @Valid RemoveRequestForm removeRequestForm) {
        shopUseCase.deleteShop(removeRequestForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ShopDetail>> getShopsByIds(@RequestParam List<Long> id) {
        List<ShopDetail> shopDetailList = shopUseCase.getShopsByIds(id);
        return ResponseEntity.ok(shopDetailList);
    }

    @Operation(summary = "샵 리뷰 등록")
    @PostMapping("/shop/review")
    public ResponseEntity<Object> requestShopReviewRegistration(@RequestBody @Valid ReviewRequest reviewRequest){

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 수정")
    @PutMapping("/shop/review")
    public ResponseEntity<Object> updateReview(@RequestBody @Valid UpdateReviewDto updateReviewDto) {

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 조회")
    @GetMapping("/shop/review")
    public ResponseEntity<Object> getShopReview(@RequestHeader(name= "memberId") long memberId, @RequestParam(name = "shopId") String shopId) {

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 삭제")
    @DeleteMapping("/shop/review")
    ResponseEntity<Object> deleteShopReview(@RequestHeader(name= "memberId") long memberId, @RequestParam(name = "shopId") String shopId) {

        return ResponseEntity.ok().build();
    }
}
