package com.luckytree.shop_service.shop.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewPhotoRepository extends JpaRepository<ReviewPhotoEntity, Long> {

    List<ReviewPhotoEntity> findAllByReviewIds (List<Long> ReviewIds);
}
