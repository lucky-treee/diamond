package com.luckytree.shop.shop.adapter.web;

import com.luckytree.shop.shop.adapter.data.review.CreateReviewDto;
import com.luckytree.shop.shop.adapter.data.review.MyReviewsDto;
import com.luckytree.shop.shop.adapter.data.review.UpdateReviewDto;
import com.luckytree.shop.shop.application.port.incoming.ReviewUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Review API", description = "후기")
@RestController
@RequestMapping("v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewUseCase reviewUseCase;

    @Operation(summary = "리뷰 등록")
    @PostMapping
    public ResponseEntity<Object> createReview(@RequestPart("createReviewDto") @Valid CreateReviewDto createReviewDto){
        Long reviewId = reviewUseCase.createReview(createReviewDto);
        return ResponseEntity.ok(reviewId);
    }
    // 리턴 타입을 ReviewResponse 로 구현

    @Operation(summary = "리뷰 사진 등록")
    @PostMapping("/{id}/photos")
    public ResponseEntity<Object> createReviewPhoto(@PathVariable("id") Long reviewId, @RequestPart("reviewPhotos") @Valid List<MultipartFile> reviewPhotos){
        reviewUseCase.createReviewPhoto(reviewId, reviewPhotos);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "리뷰 수정")
    @PatchMapping
    public ResponseEntity<Object> updateReview(@RequestBody @Valid UpdateReviewDto updateReviewDto) {
        reviewUseCase.updateReview(updateReviewDto);
        return ResponseEntity.ok().build();
    }
    // 리턴 타입을 ReviewResponse 로 구현

    @Operation(summary = "리뷰 사진 수정")
    @PatchMapping("/{id}/photos")
    public ResponseEntity<Object> updateReviewPhoto(@PathVariable("id") Long reviewId, @RequestPart("reviewPhotos") @Valid List<MultipartFile> reviewPhotos) {
        reviewUseCase.updateReviewPhoto(reviewId, reviewPhotos);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "내 리뷰 조회")
    @GetMapping
    public ResponseEntity<MyReviewsDto> getMyReviews(@RequestParam("member_id") long memberId, @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return ResponseEntity.ok(reviewUseCase.findMyReviewsById(memberId, pageable));
    }

    @Operation(summary = "리뷰 삭제")
    @DeleteMapping
    ResponseEntity<Object> deleteShopReview(@RequestHeader("Authorization") String authorization, @RequestParam(name = "shopId") String shopId) {

        return ResponseEntity.ok().build();
    }
}
