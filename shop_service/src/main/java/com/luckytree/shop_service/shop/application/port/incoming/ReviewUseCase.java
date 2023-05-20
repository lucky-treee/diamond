package com.luckytree.shop_service.shop.application.port.incoming;

import com.luckytree.shop_service.shop.adapter.data.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewUseCase {

    Long createReview(CreateReviewDto createReviewDto);

    void createReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos);

    void updateReview(UpdateReviewDto updateReviewDto);

    void updateReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos);
}
