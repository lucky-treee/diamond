package com.luckytree.shop.shop.adapter.web;

import com.luckytree.shop.shop.adapter.data.review.CreateReviewRequest;
import com.luckytree.shop.shop.adapter.data.review.MyReviewsResponse;
import com.luckytree.shop.shop.adapter.data.review.ReviewResponse;
import com.luckytree.shop.shop.adapter.data.review.UpdateReviewRequest;
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

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Tag(name = "Review API", description = "후기")
@RestController
@RequestMapping("v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewUseCase reviewUseCase;

    @Operation(summary = "리뷰 등록")
    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestPart("createReviewRequest") @Valid CreateReviewRequest createReviewRequest){
        ReviewResponse reviewResponse = reviewUseCase.createReview(createReviewRequest.toDomain());
        return ResponseEntity.ok(reviewResponse);
    }

    @Operation(summary = "리뷰 사진 등록")
    @PostMapping("/{id}/photos")
    public ResponseEntity<Object> createReviewPhoto(@PathVariable("id") Long reviewId, @RequestPart("reviewPhotos") @Valid List<MultipartFile> reviewPhotos){
        reviewUseCase.createReviewPhoto(reviewId, reviewPhotos);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "리뷰 수정")
    @PatchMapping
    public ResponseEntity<ReviewResponse> updateReview(@RequestBody @Valid UpdateReviewRequest updateReviewRequest) {
        ReviewResponse reviewResponse = reviewUseCase.updateReview(updateReviewRequest.toDomain());
        return ResponseEntity.ok(reviewResponse);
    }

    @Operation(summary = "리뷰 사진 수정")
    @PatchMapping("/{id}/photos")
    public ResponseEntity<Object> updateReviewPhoto(@PathVariable("id") Long reviewId, @RequestPart("reviewPhotos") @Valid List<MultipartFile> reviewPhotos) {
        reviewUseCase.updateReviewPhoto(reviewId, reviewPhotos);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "내 리뷰 조회")
    @GetMapping
    public ResponseEntity<MyReviewsResponse> getMyReviews(@RequestParam("memberId") Long memberId, @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return ResponseEntity.ok(reviewUseCase.findMyReviewsById(memberId, pageable));
    }

    @Operation(summary = "리뷰 삭제")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping
    public void deleteReview(@RequestParam(name = "reviewId") Long reviewId) {
        reviewUseCase.delete(reviewId);
    }
}
