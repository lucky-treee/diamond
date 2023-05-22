package com.luckytree.shop.shop.application.port.incoming;

import com.luckytree.shop.shop.adapter.data.review.MyReviewsResponse;
import com.luckytree.shop.shop.domain.review.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewUseCase {

    Long createReview(Review review);

    void createReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos);

    Long updateReview(Review review);

    void updateReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos);
  
    MyReviewsResponse findMyReviewsById(Long memberId, Pageable pageable);
}
