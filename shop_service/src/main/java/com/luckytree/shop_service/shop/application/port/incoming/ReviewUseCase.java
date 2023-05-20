package com.luckytree.shop_service.shop.application.port.incoming;

import com.luckytree.shop_service.shop.adapter.data.MyReviewsDto;
import org.springframework.data.domain.Pageable;

public interface ReviewUseCase {

    MyReviewsDto findMyReviewsById(long memberId, Pageable pageable);
}
