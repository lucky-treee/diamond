package com.luckytree.shop_service.shop.adapter.jpa;

import com.luckytree.shop_service.shop.domain.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

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

    @Column(length = 50, nullable = false)
    private Long shopId;

    @Column(length = 50, nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String content;

    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    public ReviewEntity(Review review) {
        this.shopId = review.getShopId();
        this.memberId = review.getMemberId();
        this.content = review.getContent();
    }

    public void update(String content) {
        this.content = content;
    }
}
