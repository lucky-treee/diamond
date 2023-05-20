package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.adapter.data.ReviewsResponse;
import com.luckytree.member_service.member.application.port.incoming.ReviewUseCase;
import com.luckytree.member_service.member.application.port.outgoing.ShopFeignClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService implements ReviewUseCase {

    private final ShopFeignClientPort shopFeignClientPort;

    @Transactional(readOnly = true)
    @Override
    public ReviewsResponse getMyReviews(long memberId, Pageable pageable) {
        return shopFeignClientPort.findShopMyReviewsById(memberId, pageable);
    }
}
