package com.luckytree.shop_service.shop.adapter.web.internal;

import com.luckytree.shop_service.shop.application.port.incoming.ReviewUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Internal API", description = "호출 X")
@RestController
@RequestMapping("/v1/reviews")
@RequiredArgsConstructor
public class internalReviewController {

    private final ReviewUseCase reviewUseCase;

    @DeleteMapping
    void deleteReview(@RequestParam("review_id") long reviewId) {
        reviewUseCase.deleteReview(reviewId);
    }
}
