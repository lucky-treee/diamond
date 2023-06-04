package com.luckytree.shop.shop.application.service;

import com.luckytree.shop.shop.adapter.data.review.ReviewResponse;
import com.luckytree.shop.shop.adapter.jpa.review.ReviewEntity;
import com.luckytree.shop.shop.adapter.jpa.review.ReviewPhotoEntity;
import com.luckytree.shop.shop.adapter.jpa.shop.ShopEntity;
import com.luckytree.shop.shop.application.port.incoming.ReviewUseCase;
import com.luckytree.shop.shop.application.port.outgoing.ReviewPort;
import com.luckytree.shop.shop.application.port.outgoing.ShopPort;
import com.luckytree.shop.shop.domain.review.Review;
import com.luckytree.shop.shop.domain.review.ReviewPhoto;
import com.luckytree.shop.utils.S3Util;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.exceptions.InternalServerErrorException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    private final ShopPort shopPort;

    @Transactional(readOnly = true)
    @Override
    public Page<ReviewResponse> findMyReviewsById(Object authenticationToken, Pageable pageable) {
        Long memberId = (Long) authenticationToken;
        return findMyReviews(memberId, pageable);
    }

    @Override
    public ReviewResponse createReview(Review review) {
        return new ReviewResponse(reviewPort.createReview(review));
    }

    @Override
    public List<String> createReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos) {
        return uploadPhotoToS3(reviewId, reviewPhotos);
    }

    @Override
    public ReviewResponse updateReview(Review review) {
        ReviewEntity reviewEntity = reviewPort.findReviewById(review.getId());
        if(reviewEntity.getCreateAt().plusDays(7).isBefore(LocalDateTime.now())){
            throw new RuntimeException("리뷰 수정은 7일 이내에만 가능합니다.");
        }
        return new ReviewResponse(reviewEntity.update(review.getContent(), review.getHashtag()));
    }

    @Override
    public void deleteReviewPhoto(String photoUrl){
        String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);
        s3Util.delete(fileName);
        reviewPort.deleteReviewPhotoByPhotoUrl(photoUrl);
    }

    @Override
    public void delete(Long reviewId) {
        List<String> photoUrls = reviewPort.findReviewPhotoByReviewId(reviewId).stream().map(ReviewPhotoEntity::getPhotoUrl).toList();
        photoUrls.forEach(photoUrl -> {
                    String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);
                    s3Util.delete(fileName);
                    reviewPort.deleteReviewPhotoByPhotoUrl(photoUrl);
                }
        );
        reviewPort.delete(reviewId);
    }

    private List<String> uploadPhotoToS3(Long reviewId, List<MultipartFile> reviewPhotos) {
        List<String> resultPhotoUrls = new ArrayList<>();
        List<String> photoUrls = new ArrayList<>();

        reviewPhotos.forEach(multipartFile -> {
            try {
                photoUrls.add(s3Util.upload(multipartFile));
            } catch (IOException e) {
                throw new InternalServerErrorException(e.getMessage());
            }
        });

        photoUrls.forEach(photoUrl -> resultPhotoUrls.add(reviewPort.createReviewPhoto(new ReviewPhoto(reviewId, photoUrl)).getPhotoUrl()));

        return resultPhotoUrls;
    }

    private Page<ReviewResponse> findMyReviews(Long memberId, Pageable pageable) {
        List<ReviewResponse> reviewResponses = reviewPort.findAllByMemberId(memberId, pageable).stream().map(reviewEntity -> {
            List<ReviewPhotoEntity> reviewPhotos = reviewPort.findReviewPhotoByReviewId(reviewEntity.getId());
            ShopEntity shopEntity = shopPort.getShopDetailById(reviewEntity.getShopId());
            return new ReviewResponse(reviewEntity, reviewPhotos, shopEntity);
        }).toList();
        return new PageImpl<>(reviewResponses);
    }
}
