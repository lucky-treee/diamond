package com.luckytree.shop.shop.application.port.incoming;

import com.luckytree.shop.shop.adapter.data.MyReviewsDto;
import org.springframework.data.domain.Pageable;
import com.luckytree.shop.shop.adapter.data.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewUseCase {

    Long createReview(CreateReviewDto createReviewDto);

    void createReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos);

    void updateReview(UpdateReviewDto updateReviewDto);

    void updateReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos);
  
    MyReviewsDto findMyReviewsById(long memberId, Pageable pageable);
    
    void deleteReview(long reviewId);
}
