package com.luckytree.shop_service.shop.adapter;

import com.luckytree.shop_service.shop.adapter.jpa.ReviewEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewPhotoEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewPhotoRepository;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewRepository;
import com.luckytree.shop_service.shop.application.port.outgoing.ReviewPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewAdapter implements ReviewPort {

    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    @Override
    public List<ReviewEntity> findAllByMemberId(long memberId) {
        return reviewRepository.findAllByMemberId(memberId);
    }

    @Override
    public List<ReviewPhotoEntity> findReviewPhotoByIds(List<Long> reviewIds) {
        return reviewPhotoRepository.findAllByReviewIds(reviewIds);
    }
}
