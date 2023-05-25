package com.luckytree.shop.shop.application.port.incoming;

import com.luckytree.shop.shop.adapter.data.review.ReviewResponse;
import com.luckytree.shop.shop.domain.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewUseCase {

    ReviewResponse createReview(Review review);

    void createReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos);

    ReviewResponse updateReview(Review review);

    void updateReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos);
  
    Page<ReviewResponse> findMyReviewsById(Long memberId, Pageable pageable);

    void delete(Long reviewId);
}
