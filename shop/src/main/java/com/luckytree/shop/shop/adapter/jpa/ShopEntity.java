package com.luckytree.shop.shop.adapter.jpa;

import com.luckytree.shop.common.enums.Category;
import com.luckytree.shop.common.enums.Hashtag;
import com.luckytree.shop.common.enums.ShopStatus;
import com.luckytree.shop.shop.domain.Shop;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Table(name = "shop")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ShopEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(length = 10, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ShopStatus status;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @Column(length = 50)
    @Enumerated(value = EnumType.STRING)
    private Hashtag hashtag;

    @Column(length = 150)
    private String photo;

    @Column(length = 50)
    private String contact;

    @Column(length = 50)
    private String sns;

    @Column(length = 50)
    private String homepage;

    @Column(name = "flagship_product", length = 50)
    private String flagshipProduct;

    @Column(name = "operating_start")
    private LocalTime operatingStart;

    @Column(name = "operating_end")
    private LocalTime operatingEnd;

    @Column(length = 20)
    private String holiday;

    @OneToMany(mappedBy = "shopEntity")
    private List<ShopRemoveEntity> shopRemoveEntityList;

    public ShopEntity(Shop shop) {
        this.name = shop.getName();
        this.category = shop.getCategory();
        this.status = shop.getStatus();
        this.address = shop.getAddress();
        this.lat = shop.getLat();
        this.lng = shop.getLng();
        this.photo = shop.getPhoto();
        this.contact = shop.getContact();
        this.sns = shop.getSns();
        this.homepage = shop.getHomepage();
        this.flagshipProduct = shop.getFlagshipProduct();
        this.operatingStart = shop.getOperatingStart();
        this.operatingEnd = shop.getOperatingEnd();
        this.holiday = shop.getHoliday();
    }
}
