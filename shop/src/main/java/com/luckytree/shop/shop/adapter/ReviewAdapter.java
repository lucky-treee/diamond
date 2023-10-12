package com.luckytree.shop.shop.adapter;

import com.luckytree.shop.shop.adapter.jpa.review.ReviewEntity;
import com.luckytree.shop.shop.adapter.jpa.review.ReviewPhotoEntity;
import com.luckytree.shop.shop.adapter.jpa.review.ReviewPhotoRepository;
import com.luckytree.shop.shop.adapter.jpa.review.ReviewRepository;
import com.luckytree.shop.shop.application.port.outgoing.ReviewPhotoPort;
import com.luckytree.shop.shop.application.port.outgoing.ReviewPort;
import com.luckytree.shop.shop.domain.Page;
import com.luckytree.shop.shop.domain.review.PagedReview;
import com.luckytree.shop.shop.domain.review.Review;
import com.luckytree.shop.shop.domain.review.ReviewPhoto;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewAdapter implements ReviewPort, ReviewPhotoPort {

    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    @Override
    public PagedReview findAllByMemberId(Long memberId, Page page) {
        org.springframework.data.domain.Page<Review> reviewEntities = reviewRepository.findAllByMemberId(memberId, page.toPageRequest()).map(ReviewEntity::toDomain);
        Page customPage = new Page(reviewEntities.getTotalElements(), (long) reviewEntities.getSize(), (long) reviewEntities.getNumber());

        return new PagedReview(customPage, reviewEntities.getContent());
    }

    @Override
    public PagedReview findAllByShopId(Long shopId, Page page) {
        org.springframework.data.domain.Page<Review> reviewEntities = reviewRepository.findAllByShopId(shopId, page.toPageRequest()).map(ReviewEntity::toDomain);
        Page customPage = new Page(reviewEntities.getTotalElements(), (long) reviewEntities.getSize(), (long) reviewEntities.getNumber());

        return new PagedReview(customPage, reviewEntities.getContent());
    }

    @Override
    public void delete(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public ReviewPhoto save(ReviewPhoto reviewPhoto) {
        return null;
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow(NotFoundException::new).toDomain();
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(new ReviewEntity(review)).toDomain();
    }

    @Override
    public List<ReviewPhoto> findAllByReviewId(Long reviewId) {
        return reviewPhotoRepository.findByReviewId(reviewId).stream().map(ReviewPhotoEntity::toDomain).toList();
    }

    @Override
    public void deleteByPhotoUrl(String photoUrl) {
        reviewPhotoRepository.deleteByPhotoUrl(photoUrl);
    }
}

