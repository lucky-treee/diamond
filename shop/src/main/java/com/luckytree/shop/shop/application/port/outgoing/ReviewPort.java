package com.luckytree.shop.shop.application.port.outgoing;

import com.luckytree.shop.shop.adapter.jpa.review.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.luckytree.shop.shop.adapter.jpa.review.ReviewPhotoEntity;
import com.luckytree.shop.shop.domain.review.Review;
import com.luckytree.shop.shop.domain.review.ReviewPhoto;

import java.util.List;

public interface ReviewPort {

    ReviewEntity createReview(Review review);

    void createReviewPhoto(ReviewPhoto reviewPhoto);

    ReviewEntity findReviewById(long reviewId);

    List<ReviewPhotoEntity> findReviewPhotoByReviewId(long reviewId);

    void deleteReviewPhotoByReviewId(long reviewId);
  
    void deleteReview(long reviewId);

    List<ReviewPhotoEntity> findReviewPhotoByReviewId(Long reviewId);

    ReviewEntity findById(long reviewId);
  
    Page<ReviewEntity> findAllByMemberId(long memberId, Pageable pageable);
}
