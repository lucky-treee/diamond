package com.luckytree.shop_service.shop.adapter.jpa;

import com.luckytree.shop_service.common.exceptions.BadRequestException;
import com.luckytree.shop_service.shop.domain.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "review")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shop_id", length = 20, unique = true, nullable = false)
    private Long shopId;

    @Column(name = "member_id", length = 20, unique = true, nullable = false)
    private Long memberId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "reviewEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewPhotoEntity> photos;

    public void isAlreadyDeleted() {
        if (this == null) {
            throw new BadRequestException("이미 삭제된 리뷰입니다.");
        }
    }

    public ReviewEntity(Review review) {
        this.shopId = review.getShopId();
        this.memberId = review.getMemberId();
        this.content = review.getContent();
    }
}
