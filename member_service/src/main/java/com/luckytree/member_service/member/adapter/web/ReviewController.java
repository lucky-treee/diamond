package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.member.adapter.data.ReviewsResponse;
import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.application.port.incoming.ReviewUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "리뷰", description = "리뷰 관련 API 모음")
@RestController
@RequestMapping("/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewUseCase reviewUseCase;

    @Operation(summary = "내 리뷰 조회(로그인)")
    @GetMapping("/review")
    public ResponseEntity<ReviewsResponse> getMyReviews(@RequestParam("member_id") long memberId, @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return ResponseEntity.ok(reviewUseCase.getMyReviews(memberId, pageable));

    @Operation(summary = "리뷰삭제(로그인)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/remove")
    public void deleteReview(@RequestParam("review_id") Long reviewId) {
        reviewUseCase.deleteReview(reviewId);
    }
}
