package com.luckytree.shop_service.shop.adapter;

import com.luckytree.shop_service.shop.adapter.jpa.ReviewEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewRepository;
import com.luckytree.shop_service.shop.application.port.outgoing.ReviewPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class ReviewAdapter implements ReviewPort {

    private final ReviewRepository reviewRepository;

    @Override
    public Page<ReviewEntity> findAllByMemberId(long memberId, Pageable pageable) {
        return reviewRepository.findAllByMemberId(memberId, pageable);
    }
}
