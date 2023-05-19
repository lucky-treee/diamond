package com.luckytree.shop_service.shop.application.port.outgoing;

import com.luckytree.shop_service.shop.adapter.jpa.ReviewEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewPhotoEntity;

import java.util.List;

public interface ReviewPort {

    void deleteReview(long reviewId);

    List<ReviewPhotoEntity> findReviewPhotoByReviewEntity(ReviewEntity reviewEntity);

    ReviewEntity findById(long reviewId);
}
