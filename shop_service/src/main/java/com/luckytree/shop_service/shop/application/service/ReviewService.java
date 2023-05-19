package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.common.utils.S3Util;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewPhotoEntity;
import com.luckytree.shop_service.shop.application.port.incoming.ReviewUseCase;
import com.luckytree.shop_service.shop.application.port.outgoing.ReviewPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService implements ReviewUseCase {

    private final ReviewPort reviewPort;
    private final S3Util s3Util;

    @Override
    public void deleteReview(long reviewId) {
        ReviewEntity reviewEntity = reviewPort.findById(reviewId);
        reviewEntity.isAlreadyDeleted();

        s3delete(reviewEntity);
        reviewPort.deleteReview(reviewId);
    }

    private void s3delete(ReviewEntity reviewEntity) {
        List<String> deletingPhotos = reviewPort.findReviewPhotoByReviewEntity(reviewEntity).stream().map(ReviewPhotoEntity::getPhotoUrl).map(s -> s.substring(s.lastIndexOf("/") + 1)).toList();
        deletingPhotos.stream().forEach(s -> s3Util.delete(s));
    }
}
