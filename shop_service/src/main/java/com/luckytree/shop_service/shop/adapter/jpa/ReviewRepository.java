package com.luckytree.shop_service.shop.adapter.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {


    Page<ReviewEntity> findAllByMemberId(long memberId, Pageable pageable);
}
