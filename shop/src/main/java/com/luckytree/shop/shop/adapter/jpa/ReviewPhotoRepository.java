package com.luckytree.shop.shop.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewPhotoRepository extends JpaRepository<ReviewPhotoEntity, Long> {
  
    List<ReviewPhotoEntity> findByReviewId(Long reviewId);

    void deleteByReviewId(Long reviewId);
}
