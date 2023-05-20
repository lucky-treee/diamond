package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.shop.adapter.data.MyReviewsDto;
import com.luckytree.shop_service.shop.adapter.data.ReviewDto;
import com.luckytree.shop_service.shop.application.port.incoming.ReviewUseCase;
import com.luckytree.shop_service.shop.application.port.outgoing.ReviewPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService implements ReviewUseCase {

    private final ReviewPort reviewPort;

    @Transactional(readOnly = true)
    @Override
    public MyReviewsDto findMyReviewsById(long memberId, Pageable pageable) {
        return findMyReviews(memberId, pageable);
    }

    private MyReviewsDto findMyReviews(long memberId, Pageable pageable) {
        List<ReviewDto> reviewDtos = reviewPort.findAllByMemberId(memberId, pageable).stream().map(ReviewDto::new).toList();
        return new MyReviewsDto(reviewDtos);
    }
}
