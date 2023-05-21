package com.luckytree.shop_service.shop.adapter;

import com.luckytree.shop_service.shop.adapter.jpa.ReviewEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewRepository;
import com.luckytree.shop_service.shop.application.port.outgoing.ReviewPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.luckytree.shop_service.common.exceptions.NotFoundException;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewPhotoEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewPhotoRepository;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewRepository;
import com.luckytree.shop_service.shop.application.port.outgoing.ReviewPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.luckytree.shop_service.shop.adapter.jpa.*;
import com.luckytree.shop_service.shop.application.port.outgoing.ReviewPort;
import com.luckytree.shop_service.shop.domain.Review;
import com.luckytree.shop_service.shop.domain.ReviewPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewAdapter implements ReviewPort {

    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    @Override
    public Page<ReviewEntity> findAllByMemberId(long memberId, Pageable pageable) {
        return reviewRepository.findAllByMemberId(memberId, pageable);
    }

    @Override
    public void deleteReview(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public ReviewEntity findById(long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException("존재하지 않는 리뷰입니다"));
    }
  
    @Override
    public ReviewEntity createReview(Review review) {
        return reviewRepository.save(new ReviewEntity(review));
    }

    @Override
    public void createReviewPhoto(ReviewPhoto reviewPhoto) {
        reviewPhotoRepository.save(new ReviewPhotoEntity(reviewPhoto));
    }

    @Override
    public ReviewEntity findReviewById(long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException("해당 reviewId에 일치하는 ReviewEntity가 없습니다. id: " + reviewId));
    }

    @Override
    public List<ReviewPhotoEntity> findReviewPhotoByReviewId(long reviewId) {
        return reviewPhotoRepository.findByReviewId(reviewId);
    }

    @Override
    public void deleteReviewPhotoByReviewId(long reviewId) {
        reviewPhotoRepository.deleteByReviewId(reviewId);
    }
}

