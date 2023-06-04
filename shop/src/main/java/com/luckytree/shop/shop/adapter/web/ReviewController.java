package com.luckytree.shop.shop.adapter.web;

import com.luckytree.shop.shop.adapter.data.review.CreateReviewRequest;
import com.luckytree.shop.shop.adapter.data.review.ReviewResponse;
import com.luckytree.shop.shop.adapter.data.review.UpdateReviewRequest;
import com.luckytree.shop.shop.application.port.incoming.ReviewUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.jwt.AuthenticationToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReviewResponse> createReview(
            @RequestBody @Valid CreateReviewRequest createReviewRequest,
            @RequestPart("reviewPhotos") @Valid List<MultipartFile> reviewPhotos,
            @AuthenticationPrincipal AuthenticationToken authenticationToken) {
        ReviewResponse reviewResponse = reviewUseCase.createReview(createReviewRequest.toDomain(Long.valueOf(authenticationToken.getPrincipal())));
        List<String> reviewPhotoUrls = reviewUseCase.createReviewPhoto(reviewResponse.getId(), reviewPhotos);

        reviewResponse.setPhotoUrl(reviewPhotoUrls);

        return ResponseEntity.ok(reviewResponse);
    }

    @Operation(summary = "리뷰 수정")
    @PatchMapping
    public ResponseEntity<ReviewResponse> updateReview(@RequestBody @Valid UpdateReviewRequest updateReviewRequest) {
        ReviewResponse reviewResponse = reviewUseCase.updateReview(updateReviewRequest.toDomain());
        return ResponseEntity.ok(reviewResponse);
    }

    @Operation(summary = "내 리뷰 조회")
    @GetMapping("my")
    public ResponseEntity<Page<ReviewResponse>> getMyReviews(@AuthenticationPrincipal AuthenticationToken authenticationToken, Pageable pageable) {
        return ResponseEntity.ok(reviewUseCase.findMyReviewsById(authenticationToken, pageable));
    }

    /*@Operation(summary = "리뷰 조회")
    @GetMapping
    public ResponseEntity<Page<ReviewResponse>> getReviews(Long shopId, Pageable pageable) {
        return ResponseEntity.ok(reviewUseCase.findById(shopId, pageable));
    }*/

    @Operation(summary = "리뷰 삭제")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping
    public void deleteReview(@RequestParam(name = "reviewId") Long reviewId) {
        reviewUseCase.delete(reviewId);
    }

    @Operation(summary = "리뷰 사진 변경시, N건 등록")
    @PostMapping(value = "/{id}/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createReviewPhoto(@PathVariable("id") Long reviewId, @RequestPart("reviewPhotos") @Valid List<MultipartFile> reviewPhotos){
        reviewUseCase.createReviewPhoto(reviewId, reviewPhotos);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "리뷰 사진 변경시, 단건 삭제")
    @DeleteMapping(value = "/photos")
    public ResponseEntity<Object> deleteReviewPhoto(@RequestParam String photoUrl) {
        reviewUseCase.deleteReviewPhoto(photoUrl);
        return ResponseEntity.ok().build();
    }
}
