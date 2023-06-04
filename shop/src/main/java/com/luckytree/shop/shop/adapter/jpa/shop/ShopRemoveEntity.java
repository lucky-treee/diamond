package com.luckytree.shop.shop.adapter.jpa.shop;

import com.luckytree.shop.shop.domain.shop.ShopRemoveDetail;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "shop_remove")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ShopRemoveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    public ShopRemoveEntity(ShopRemoveDetail shopRemoveDetail) {
        this.comment = shopRemoveDetail.getComment();
    }
}
