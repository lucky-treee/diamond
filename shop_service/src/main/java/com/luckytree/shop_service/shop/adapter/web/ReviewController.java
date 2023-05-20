package com.luckytree.shop_service.shop.adapter.web;

import com.luckytree.shop_service.shop.adapter.data.*;
import com.luckytree.shop_service.shop.application.port.incoming.ReviewUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Shop API", description = "프론트엔드 사용")
@RestController
@RequestMapping("/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewUseCase reviewUseCase;

    @Operation(summary = "샵 리뷰 등록(로그인)")
    @PostMapping("/review")
    public ResponseEntity<Object> createReview(@RequestPart("createReviewDto") @Valid CreateReviewDto createReviewDto){
        Long reviewId = reviewUseCase.createReview(createReviewDto);
        return ResponseEntity.ok(reviewId);
    }

    @Operation(summary = "샵 리뷰 사진 등록(로그인)")
    @PostMapping("/review/photo")
    public ResponseEntity<Object> createReviewPhoto(@RequestPart("reviewId") @Valid Long reviewId, @RequestPart("reviewPhotos") @Valid List<MultipartFile> reviewPhotos){
        reviewUseCase.createReviewPhoto(reviewId, reviewPhotos);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 수정(로그인)")
    @PatchMapping("/review")
    public ResponseEntity<Object> updateReview(@RequestPart("updateReviewDto") @Valid UpdateReviewDto updateReviewDto) {
        reviewUseCase.updateReview(updateReviewDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 사진 수정(로그인)")
    @PatchMapping("/review/photo")
    public ResponseEntity<Object> updateReviewPhoto(@RequestPart("reviewId") @Valid Long reviewId, @RequestPart("reviewPhotos") @Valid List<MultipartFile> reviewPhotos) {
        reviewUseCase.updateReviewPhoto(reviewId, reviewPhotos);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 조회")
    @GetMapping("/review")
    public ResponseEntity<Object> findShopReviewByMemberIdOrShopId(@RequestHeader("Authorization") String authorization, @RequestParam(name = "shopId") String shopId) {

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 리뷰 삭제(로그인)")
    @DeleteMapping("/review")
    ResponseEntity<Object> deleteShopReview(@RequestHeader("Authorization") String authorization, @RequestParam(name = "shopId") String shopId) {

        return ResponseEntity.ok().build();
    }
}
