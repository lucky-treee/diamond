package com.luckytree.shop.shop.application.service;

import com.luckytree.shop.common.utils.S3Util;
import com.luckytree.shop.shop.adapter.data.review.CreateReviewDto;
import com.luckytree.shop.shop.adapter.data.review.UpdateReviewDto;
import com.luckytree.shop.shop.adapter.jpa.review.ReviewEntity;
import com.luckytree.shop.shop.adapter.jpa.review.ReviewPhotoEntity;
import com.luckytree.shop.shop.adapter.jpa.shop.ShopEntity;
import com.luckytree.shop.shop.application.port.incoming.ReviewUseCase;
import com.luckytree.shop.shop.application.port.outgoing.ReviewPort;
import com.luckytree.shop.shop.application.port.outgoing.ShopPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.luckytree.shop.shop.adapter.data.review.MyReviewsDto;
import com.luckytree.shop.shop.adapter.data.review.ReviewDto;
import org.springframework.data.domain.Pageable;

import com.luckytree.shop.shop.domain.review.ReviewPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService implements ReviewUseCase {

    private final ReviewPort reviewPort;
    private final S3Util s3Util;
    private final ShopPort shopPort;
  
    @Transactional(readOnly = true)
    @Override
    public MyReviewsDto findMyReviewsById(long memberId, Pageable pageable) {
        return findMyReviews(memberId, pageable);
    }

    private MyReviewsDto findMyReviews(long memberId, Pageable pageable) {
        List<ReviewDto> reviewDtos = reviewPort.findAllByMemberId(memberId, pageable).stream().map(reviewEntity -> {
            List<ReviewPhotoEntity> reviewPhotos = reviewPort.findReviewPhotoByReviewId(reviewEntity.getId());
            ShopEntity shopEntity = shopPort.getShopDetailById(reviewEntity.getShopId());
            return new ReviewDto(reviewEntity, reviewPhotos, shopEntity);
        }).toList();
        return new MyReviewsDto(reviewDtos);
    }

    private void s3delete(ReviewEntity reviewEntity) {
        List<String> deletingPhotos = reviewPort.findReviewPhotoByReviewId(reviewEntity.getId()).stream().map(ReviewPhotoEntity::getPhotoUrl).map(s -> s.substring(s.lastIndexOf("/") + 1)).toList();
        deletingPhotos.forEach(s3Util::delete);
    }

    @Override
    public Long createReview(CreateReviewDto createReviewDto) {
        return reviewPort.createReview(createReviewDto.toDomain()).getId();
    }

    @Override
    public void createReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos) {
        List<String> photoUrls = new ArrayList<>();
        reviewPhotos.forEach(multipartFile -> {
            try {
                photoUrls.add(s3Util.upload(multipartFile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        photoUrls.forEach(s ->{
            reviewPort.createReviewPhoto(new ReviewPhoto(reviewId, s));
        });
    }

    @Override
    public void updateReview(UpdateReviewDto updateReviewDto) {
        ReviewEntity reviewEntity = reviewPort.findReviewById(updateReviewDto.getReviewId());
        if(reviewEntity.getCreateAt().plusDays(7).isBefore(LocalDateTime.now())){
            throw new RuntimeException("리뷰 수정은 7일 이내에만 가능합니다.");
        }
        reviewEntity.update(updateReviewDto.getContent(), updateReviewDto.getHashtag());
    }

    @Override
    public void updateReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos){
        List<String> deletingPhotos = reviewPort.findReviewPhotoByReviewId(reviewId).stream().map(ReviewPhotoEntity :: getPhotoUrl).map(s -> s.substring(s.lastIndexOf("/")+1)).toList();
        deletingPhotos.forEach(s3Util::delete);
        reviewPort.deleteReviewPhotoByReviewId(reviewId);
        List<String> photoUrls = new ArrayList<>();
        reviewPhotos.forEach(multipartFile -> {
            try {
                photoUrls.add(s3Util.upload(multipartFile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        photoUrls.forEach(s ->{
            reviewPort.createReviewPhoto(new ReviewPhoto(reviewId, s));
        });
    }
}
