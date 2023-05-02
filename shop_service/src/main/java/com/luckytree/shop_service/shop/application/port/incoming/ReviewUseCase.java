package com.luckytree.shop_service.shop.application.port.incoming;

import com.luckytree.shop_service.shop.adapter.data.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewUseCase {

    Long createReview(String authorization, CreateReviewDto createReviewDto);

    void createReviewPhoto(String authorization, CreateReviewPhotoDto createReviewPhotoDto);

    void updateReview(String authorization, UpdateReviewDto updateReviewDto);

    void updateReviewPhoto(String authorization, UpdateReviewPhotoDto updateReviewPhotoDto);

    void createReviewPhotoTest(Long reviewId, MultipartFile reviewPhoto);

    void createReviewPhotosTest(Long reviewId, List<MultipartFile> reviewPhotos);

    Long createReviewTest(CreateReviewDto createReviewDto);

    void updateReviewPhotosTest(Long reviewId, List<MultipartFile> reviewPhotos);
}