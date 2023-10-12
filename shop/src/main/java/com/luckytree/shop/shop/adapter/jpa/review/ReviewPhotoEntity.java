package com.luckytree.shop.shop.adapter.jpa.review;

import com.luckytree.shop.shop.domain.review.ReviewPhoto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "review_photo")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class ReviewPhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "review_id")
    private Long reviewId;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    public ReviewPhoto toDomain() {
        return new ReviewPhoto(id, reviewId, photoUrl);
    }
}
