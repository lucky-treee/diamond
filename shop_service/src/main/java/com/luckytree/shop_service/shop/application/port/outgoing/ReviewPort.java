package com.luckytree.shop_service.shop.application.port.outgoing;

import com.luckytree.shop_service.shop.adapter.jpa.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewPhotoEntity;
import com.luckytree.shop_service.shop.domain.Review;
import com.luckytree.shop_service.shop.domain.ReviewPhoto;

import java.util.List;

public interface ReviewPort {

    ReviewEntity createReview(Review review);

    void createReviewPhoto(ReviewPhoto reviewPhoto);

    ReviewEntity findReviewById(long reviewId);

    List<ReviewPhotoEntity> findReviewPhotoByReviewId(long reviewId);

    void deleteReviewPhotoByReviewId(long reviewId);
  
    void deleteReview(long reviewId);

    List<ReviewPhotoEntity> findReviewPhotoByReviewEntity(ReviewEntity reviewEntity);

    ReviewEntity findById(long reviewId);
  
    Page<ReviewEntity> findAllByMemberId(long memberId, Pageable pageable);
}
