package com.luckytree.shop.shop.application.port.outgoing;

import com.luckytree.shop.shop.domain.review.ReviewPhoto;

import java.util.List;

public interface ReviewPhotoPort {
    ReviewPhoto save(ReviewPhoto reviewPhoto);

    List<ReviewPhoto> findAllByReviewId(Long reviewId);

    void deleteByPhotoUrl(String photoUrl);
}
