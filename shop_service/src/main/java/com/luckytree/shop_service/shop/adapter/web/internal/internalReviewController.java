package com.luckytree.shop_service.shop.adapter.web.internal;

import com.luckytree.shop_service.shop.adapter.data.MyReviewsDto;
import com.luckytree.shop_service.shop.application.port.incoming.ReviewUseCase;
import com.luckytree.shop_service.shop.application.port.incoming.ShopUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Internal API", description = "호출 X")
@RestController
@RequestMapping("/v1/reviews")
@RequiredArgsConstructor
public class internalReviewController {

    private final ReviewUseCase reviewUseCase;

    @PostMapping("/shops")
    public MyReviewsDto findMyReviewsById(@RequestParam("member_id") long memberId, Pageable pageable) {
        return reviewUseCase.findMyReviewsById(memberId, pageable);
    }
}
