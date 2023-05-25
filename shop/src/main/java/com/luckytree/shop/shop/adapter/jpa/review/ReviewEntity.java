package com.luckytree.shop.shop.adapter.jpa.review;

import com.luckytree.shop.shop.domain.review.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import luckytree.poom.core.enums.ShopHashtag;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "review")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
  
    @JoinColumn(name = "shop_id")
    private Long shopId;

    @Column(name = "member_id", length = 20, unique = true, nullable = false)
    private Long memberId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
  
    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updateAt;
  
    @Column(length = 50)
    @Enumerated(value = EnumType.STRING)
    private ShopHashtag hashtag;
  
    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    public ReviewEntity(Review review) {
        this.memberId = review.getMemberId();
        this.content = review.getContent();
        this.hashtag = review.getHashtag();
        this.shopId = review.getShopId();
    }

    public ReviewEntity update(String content, ShopHashtag hashtag) {
        this.content = content;
        this.hashtag = hashtag;
        return this;
    }
}
