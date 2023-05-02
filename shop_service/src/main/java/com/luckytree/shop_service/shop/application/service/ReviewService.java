package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.common.utils.S3Util;
import com.luckytree.shop_service.common.utils.TokenUtil;
import com.luckytree.shop_service.shop.adapter.data.*;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewPhotoEntity;
import com.luckytree.shop_service.shop.application.port.incoming.ReviewUseCase;
import com.luckytree.shop_service.shop.application.port.outgoing.ReviewPort;
import com.luckytree.shop_service.shop.domain.ReviewPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    public Long createReview(String authorization, CreateReviewDto createReviewDto) {
        Long memberId = TokenUtil.parseMemberId(authorization);
        createReviewDto.setMemberId(memberId);
        return reviewPort.createReview(createReviewDto.toDomain()).getId();
    }

    @Override
    public void createReviewPhoto(String authorization, CreateReviewPhotoDto createReviewPhotoDto) {
        TokenUtil.validateAuthorization(authorization);
        List<String> photoUrls = new ArrayList<>();
        createReviewPhotoDto.getReviewPhoto().stream().forEach(multipartFile -> {
            try {
                photoUrls.add(s3Util.upload(multipartFile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        photoUrls.stream().forEach(s ->{
            reviewPort.createReviewPhoto(new ReviewPhoto(createReviewPhotoDto.getReviewId(), s));
        });
    }


    @Override
    public void updateReview(String authorization, UpdateReviewDto updateReviewDto) {
        TokenUtil.validateAuthorization(authorization);
        ReviewEntity reviewEntity = reviewPort.findReviewById(updateReviewDto.getReviewId());
        if(reviewEntity.getCreateAt().plusDays(7).isBefore(LocalDateTime.now())){
            throw new RuntimeException("리뷰 수정은 7일 이내에만 가능합니다.");
        }
        reviewEntity.update(updateReviewDto.getContent());
    }

    @Override
    public void updateReviewPhoto(String authorization, UpdateReviewPhotoDto updateReviewPhotoDto){
        TokenUtil.validateAuthorization(authorization);
        Long reviewId = updateReviewPhotoDto.getReviewId();
        List<String> deletingPhotos = reviewPort.findReviewPhotoByReviewId(reviewId).stream().map(ReviewPhotoEntity :: getPhotoUrl).map(s -> s.substring(s.lastIndexOf("/")+1)).toList();
        deletingPhotos.stream().forEach(s -> s3Util.delete(s));
        reviewPort.deleteReviewPhotoByReviewId(reviewId);
        List<String> photoUrls = new ArrayList<>();
        updateReviewPhotoDto.getReviewPhoto().stream().forEach(multipartFile -> {
            try {
                photoUrls.add(s3Util.upload(multipartFile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        photoUrls.stream().forEach(s ->{
            reviewPort.createReviewPhoto(new ReviewPhoto(reviewId, s));
        });
    }
}
