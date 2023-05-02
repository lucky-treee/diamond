package com.luckytree.shop_service.shop.adapter;

import com.luckytree.shop_service.shop.adapter.jpa.*;
import com.luckytree.shop_service.shop.application.port.outgoing.ReviewPort;
import com.luckytree.shop_service.shop.domain.Review;
import com.luckytree.shop_service.shop.domain.ReviewPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewAdapter implements ReviewPort {

    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    @Override
    public ReviewEntity createReview(Review review) {
        return reviewRepository.save(new ReviewEntity(review));
    }

    @Override
    public void createReviewPhoto(ReviewPhoto reviewPhoto) {
        reviewPhotoRepository.save(new ReviewPhotoEntity(reviewPhoto));
    }

    @Override
    public ReviewEntity updateReview(Review review) {
        ReviewEntity reviewEntity = reviewRepository.findById(review.getId()).orElseThrow(() -> new NotFoundException("해당 reviewId에 일치하는 ReviewEntity가 없습니다. id: " + review.getId()));
        reviewEntity.update(review.getContent());
        return reviewEntity;
    }

    @Override
    public void updateReviewPhoto(ReviewPhoto reviewPhoto) {
        //ReviewPhotoEntity reviewPhotoEntity = reviewPhotoRepository.findByReviewId(reviewPhoto.getReviewId());
        //reviewPhotoEntity.update(reviewPhoto.getPhotoUrl());
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