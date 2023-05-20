package com.luckytree.member_service.member.adapter.feign;

import com.luckytree.member_service.common.config.FeignClientConfig;
import com.luckytree.member_service.member.adapter.data.BookmarksResponse;
import com.luckytree.member_service.member.adapter.data.FindBookmarkedShops;
import com.luckytree.member_service.member.adapter.data.ReviewsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "shop-service", configuration = {FeignClientConfig.class})
public interface ShopFeignClient {

    @PostMapping("/v1/bookmarks/shops")
    BookmarksResponse findBookmarksByIds(@RequestBody FindBookmarkedShops FindBookmarkedShops);

    @PostMapping("v1/reviews/shops")
    ReviewsResponse findShopMyReviewsByIds(@RequestParam("member_id") long memberId, Pageable pageable);
    
  @DeleteMapping("/v1/reviews")
    void deleteReview(@RequestParam("review_id") long reviewId);
}
