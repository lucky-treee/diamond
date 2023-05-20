package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.application.port.incoming.ReviewUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "리뷰", description = "리뷰 관련 API 모음")
@RestController
@RequestMapping("/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewUseCase reviewUseCase;

    //TODO TokenUtil
    @Operation(summary = "리뷰삭제(로그인)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/remove")
    public void deleteReview(@RequestParam("review_id") Long reviewId) {
        reviewUseCase.deleteReview(reviewId);
    }
}
