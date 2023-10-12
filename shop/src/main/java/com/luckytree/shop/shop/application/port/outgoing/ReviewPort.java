package com.luckytree.shop.shop.application.port.outgoing;

import com.luckytree.shop.shop.domain.Page;
import com.luckytree.shop.shop.domain.review.PagedReview;
import com.luckytree.shop.shop.domain.review.Review;

public interface ReviewPort {

    Review save(Review review);

    Review findById(Long id);

    PagedReview findAllByShopId(Long shopId, Page page);

    void delete(Long reviewId);

    PagedReview findAllByMemberId(Long memberId, Page page);
}
