package com.luckytree.shop_service.shop.adapter.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "shop_remove")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ShopRemoveEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    public ShopRemoveEntity(ShopEntity shopEntity, String comment) {
        this.shopEntity = shopEntity;
        this.comment = comment;
    }
}
