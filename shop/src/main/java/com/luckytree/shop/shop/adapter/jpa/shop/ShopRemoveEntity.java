package com.luckytree.shop.shop.adapter.jpa.shop;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import luckytree.poom.core.enums.ShopHashtag;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Table(name = "shop_remove")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ShopRemoveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    public ShopRemoveEntity(ShopEntity shopEntity, String comment) {
        this.shopEntity = shopEntity;
        this.comment = comment;
    }
}
