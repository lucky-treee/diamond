package com.luckytree.shop_service.shop.adapter.jpa;

import com.luckytree.shop_service.shop.domain.ReviewPhoto;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "review_photo")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
public class ReviewPhotoEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 50, nullable = false, name = "review_id")
    private Long reviewId;

    @Column(nullable = false, name = "photo_url")
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
