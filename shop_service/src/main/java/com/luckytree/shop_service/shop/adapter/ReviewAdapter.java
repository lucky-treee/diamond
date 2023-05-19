package com.luckytree.shop_service.shop.adapter;

import com.luckytree.shop_service.common.exceptions.NotFoundException;
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
    public void deleteReview(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<ReviewPhotoEntity> findReviewPhotoByReviewEntity(ReviewEntity reviewEntity) {
        return reviewPhotoRepository.findByReviewEntity(reviewEntity);
    }

    @Override
    public ReviewEntity findById(long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException("존재하지 않는 리뷰입니다"));
    }
}
