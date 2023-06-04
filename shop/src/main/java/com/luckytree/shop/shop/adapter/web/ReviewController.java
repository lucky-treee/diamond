package com.luckytree.shop.shop.adapter.web;

import com.luckytree.shop.shop.adapter.data.PageRequest;
import com.luckytree.shop.shop.adapter.data.review.CreateReviewRequest;
import com.luckytree.shop.shop.adapter.data.review.CreateReviewResponse;
import com.luckytree.shop.shop.adapter.data.review.ReviewResponse;
import com.luckytree.shop.shop.adapter.data.review.UpdateReviewRequest;
import com.luckytree.shop.shop.application.port.incoming.ReviewUseCase;
import com.luckytree.shop.shop.domain.review.PagedReview;
import com.luckytree.shop.shop.domain.review.Review;
import com.luckytree.shop.shop.domain.review.ReviewPhoto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.jwt.AuthenticationToken;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Tag(name = "Review", description = "후기")
@RestController
@RequestMapping("v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewUseCase reviewUseCase;

    @Operation(summary = "리뷰 등록")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreateReviewResponse> createReview(
            @RequestBody @Valid CreateReviewRequest createReviewRequest,
            @RequestPart("reviewPhotos") @Valid List<MultipartFile> reviewPhotos,
            @AuthenticationPrincipal AuthenticationToken authenticationToken) {
        Review review = reviewUseCase.create(createReviewRequest.toDomain(Long.valueOf(authenticationToken.getPrincipal())));

        List<ReviewPhoto> reviewPhotoList = reviewUseCase.createReviewPhoto(review.getId(), reviewPhotos);

        return ResponseEntity.ok(new CreateReviewResponse(review, reviewPhotoList));
    }

    @Operation(summary = "내 리뷰 목록 조회")
    @GetMapping("my")
    public ResponseEntity<PagedReview> getMyReviews(@AuthenticationPrincipal AuthenticationToken authenticationToken, PageRequest pageRequest) {
        return ResponseEntity.ok(reviewUseCase.getReviewsByMemberId(Long.valueOf(authenticationToken.getPrincipal()), pageRequest.toDomain()));
    }

    @Operation(summary = "리뷰 목록 조회 by shop id")
    @GetMapping
    public ResponseEntity<PagedReview> getReviews(Long shopId, PageRequest pageRequest) {
        return ResponseEntity.ok(reviewUseCase.getReviewsByShopId(shopId, pageRequest.toDomain()));
    }

    @Operation(summary = "리뷰 수정")
    @PatchMapping
    public ResponseEntity<ReviewResponse> updateReview(@RequestBody @Valid UpdateReviewRequest updateReviewRequest) {
        Review review = reviewUseCase.update(updateReviewRequest.toReviewDetail());
        return ResponseEntity.ok(new ReviewResponse(review));
    }

    @Operation(summary = "리뷰 삭제")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteReview(@PathVariable("id") Long id) {
        reviewUseCase.delete(id);
    }

    @Operation(summary = "리뷰 사진 변경시, N건 등록")
    @PostMapping(value = "{id}/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createReviewPhoto(@PathVariable("id") Long reviewId, @RequestPart("reviewPhotos") @Valid List<MultipartFile> reviewPhotos) {
        reviewUseCase.createReviewPhoto(reviewId, reviewPhotos);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "리뷰 사진 변경시, 단건 삭제")
    @DeleteMapping(value = "photos")
    public ResponseEntity<Object> deleteReviewPhoto(@RequestParam String photoUrl) {
        reviewUseCase.deleteReviewPhoto(photoUrl);
        return ResponseEntity.ok().build();
    }
}
