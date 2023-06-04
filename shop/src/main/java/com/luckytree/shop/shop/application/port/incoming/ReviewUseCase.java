package com.luckytree.shop.shop.application.port.incoming;

import com.luckytree.shop.shop.domain.Page;
import com.luckytree.shop.shop.domain.review.PagedReview;
import com.luckytree.shop.shop.domain.review.Review;
import com.luckytree.shop.shop.domain.review.ReviewDetail;
import com.luckytree.shop.shop.domain.review.ReviewPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewUseCase {

    Review create(Review review);

    PagedReview getReviewsByMemberId(Long memberId, Page page);

    PagedReview getReviewsByShopId(Long shopId, Page page);

    Review update(ReviewDetail reviewDetail);

    void delete(Long id);

    List<ReviewPhoto> createReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos);

    void deleteReviewPhoto(String photoUrl);
}
