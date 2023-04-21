package com.luckytree.shop_service.shop.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findAllByMemberId(long memberId);
}
