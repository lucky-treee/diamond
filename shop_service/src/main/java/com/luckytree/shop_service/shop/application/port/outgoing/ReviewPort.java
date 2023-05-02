package com.luckytree.shop_service.shop.application.port.outgoing;

import com.luckytree.shop_service.shop.adapter.jpa.ReviewEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewPhotoEntity;
import com.luckytree.shop_service.shop.domain.Review;
import com.luckytree.shop_service.shop.domain.ReviewPhoto;

import java.util.List;

public interface ReviewPort {

    ReviewEntity createReview(Review review);

    void createReviewPhoto(ReviewPhoto reviewPhoto);

    ReviewEntity updateReview(Review review);

    void updateReviewPhoto(ReviewPhoto reviewPhoto);

    ReviewEntity findReviewById(long reviewId);

    List<ReviewPhotoEntity> findReviewPhotoByReviewId(long reviewId);

    void deleteReviewPhotoByReviewId(long reviewId);
}