package com.luckytree.shop_service.shop.adapter.jpa;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.luckytree.shop_service.shop.domain.ReviewPhoto;
import lombok.*;

@Table(name = "review_photo")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@ToString
public class ReviewPhotoEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "review_id")
    private Long reviewId;
  
    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    public ReviewPhotoEntity(ReviewPhoto reviewPhoto) {
        this.reviewId = reviewPhoto.getReviewId();
        this.photoUrl = reviewPhoto.getPhotoUrl();
    }

    public void update(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public boolean isEmpty(){
        if(photoUrl.isBlank()) return true;
        return false;
    }
}
