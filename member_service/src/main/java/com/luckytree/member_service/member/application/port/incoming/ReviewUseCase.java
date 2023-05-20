package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.ReviewsResponse;
import org.springframework.data.domain.Pageable;


public interface ReviewUseCase {
  
    ReviewsResponse getMyReviews(long memberId, Pageable pageable);
    void deleteReview(long reviewId);
}
