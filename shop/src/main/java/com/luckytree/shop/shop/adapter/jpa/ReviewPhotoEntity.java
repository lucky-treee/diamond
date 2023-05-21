package com.luckytree.shop.shop.adapter.jpa;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.luckytree.shop.shop.domain.ReviewPhoto;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewEntity reviewEntity;
  
    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    public ReviewPhotoEntity(ReviewPhoto reviewPhoto) {
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
