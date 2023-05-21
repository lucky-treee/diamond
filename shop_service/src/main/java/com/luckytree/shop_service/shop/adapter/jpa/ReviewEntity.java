package com.luckytree.shop_service.shop.adapter.jpa;

import com.luckytree.shop_service.common.exceptions.BadRequestException;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.shop.domain.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "review")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReviewEntity extends BaseTimeEntity {

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
    private Hashtag hashtag;
  
    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    public void isAlreadyDeleted() {
        if (this == null) {
            throw new BadRequestException("이미 삭제된 리뷰입니다.");
        }
    }

    public ReviewEntity(Review review) {
        this.shopId = review.getShopId();
        this.memberId = review.getMemberId();
        this.content = review.getContent();
        this.hashtag = review.getHashtag();
    }

    public void update(String content, Hashtag hashtag) {
        this.content = content;
        this.hashtag = hashtag;
    }
}
