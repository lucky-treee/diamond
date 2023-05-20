package com.luckytree.shop_service.shop.application.port.outgoing;

import com.luckytree.shop_service.shop.adapter.jpa.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ReviewPort {

    Page<ReviewEntity> findAllByMemberId(long memberId, Pageable pageable);
}
