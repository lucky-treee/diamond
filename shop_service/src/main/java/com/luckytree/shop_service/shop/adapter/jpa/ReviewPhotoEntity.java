package com.luckytree.shop_service.shop.adapter.jpa;

import com.luckytree.shop_service.shop.domain.ReviewPhoto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "review_photo")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReviewPhotoEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 50, nullable = false)
    private Long reviewId;

    @Column(nullable = false)
    private String photoURL;

    public ReviewPhotoEntity(ReviewPhoto reviewPhoto) {
        this.reviewId = reviewPhoto.getReviewId();
        this.photoURL = reviewPhoto.getPhotoUrl();
    }
}
