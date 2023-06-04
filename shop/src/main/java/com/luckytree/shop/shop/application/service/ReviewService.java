package com.luckytree.shop.shop.application.service;

import com.luckytree.shop.shop.application.port.incoming.ReviewUseCase;
import com.luckytree.shop.shop.application.port.outgoing.ReviewPhotoPort;
import com.luckytree.shop.shop.application.port.outgoing.ReviewPort;
import com.luckytree.shop.shop.domain.Page;
import com.luckytree.shop.shop.domain.review.PagedReview;
import com.luckytree.shop.shop.domain.review.Review;
import com.luckytree.shop.shop.domain.review.ReviewDetail;
import com.luckytree.shop.shop.domain.review.ReviewPhoto;
import com.luckytree.shop.utils.S3Util;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.exceptions.InternalServerErrorException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService implements ReviewUseCase {

    private final ReviewPort reviewPort;
    private final S3Util s3Util;
    private final ReviewPhotoPort reviewPhotoPort;

    @Override
    public Review create(Review review) {
        return reviewPort.save(review);
    }

    @Transactional(readOnly = true)
    @Override
    public PagedReview getReviewsByMemberId(Long memberId, Page page) {
        PagedReview pagedReview = reviewPort.findAllByMemberId(memberId, page);
        pagedReview.getReviews().forEach(review -> review.setReviewPhotos(reviewPhotoPort.findAllByReviewId(review.getId())));

        return pagedReview;
    }

    @Transactional(readOnly = true)
    @Override
    public PagedReview getReviewsByShopId(Long shopId, Page page) {
        PagedReview pagedReview = reviewPort.findAllByShopId(shopId, page);
        pagedReview.getReviews().forEach(review -> review.setReviewPhotos(reviewPhotoPort.findAllByReviewId(review.getId())));

        return pagedReview;
    }

    @Override
    public Review update(ReviewDetail reviewDetail) {
        Review review = reviewPort.findById(reviewDetail.getId());
        review.check();
        review.update(reviewDetail);
        return review;
    }

    @Override
    public void delete(Long id) {
        List<String> photoUrls = reviewPhotoPort.findAllByReviewId(id).stream().map(ReviewPhoto::getPhotoUrl).toList();

        photoUrls.forEach(photoUrl -> {
                    String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);
                    s3Util.delete(fileName);
                    reviewPhotoPort.deleteByPhotoUrl(photoUrl);
                }
        );

        reviewPort.delete(id);
    }

    @Override
    public List<ReviewPhoto> createReviewPhoto(Long reviewId, List<MultipartFile> reviewPhotos) {
        List<ReviewPhoto> reviewPhotoList = new ArrayList<>();
        List<String> photoUrls = new ArrayList<>();

        reviewPhotos.forEach(multipartFile -> {
            try {
                photoUrls.add(s3Util.upload(multipartFile));
            } catch (IOException e) {
                throw new InternalServerErrorException(e.getMessage());
            }
        });

        photoUrls.forEach(photoUrl -> reviewPhotoList.add(reviewPhotoPort.save(new ReviewPhoto(reviewId, photoUrl))));

        return reviewPhotoList;
    }

    @Override
    public void deleteReviewPhoto(String photoUrl) {
        String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);
        s3Util.delete(fileName);
        reviewPhotoPort.deleteByPhotoUrl(photoUrl);
    }
}
