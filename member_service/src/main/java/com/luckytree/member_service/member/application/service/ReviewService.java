package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.application.port.incoming.ReviewUseCase;
import com.luckytree.member_service.member.application.port.outgoing.ShopFeignClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService implements ReviewUseCase {

    private final ShopFeignClientPort shopFeignClientPort;

    @Override
    public void deleteReview(long reviewId) {
        shopFeignClientPort.deleteReview(reviewId);
    }
}
