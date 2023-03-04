package com.luckytree.shop_service.shop.adapter.persistence;

import com.luckytree.shop_service.shop.adapter.data.ShopRequest;
import com.luckytree.shop_service.shop.domain.Hashtag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Table(name = "shop_temp")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ShopTempEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50)
    private String category;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(name = "lat", nullable = false)
    private Double lat;

    @Column(name = "lng", nullable = false)
    private Double lng;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    public ShopTempEntity(ShopRequest shopRequest, Long shopId) {
        this.name = shopRequest.getShopName();
        this.category = shopRequest.getCategory();
        this.address = shopRequest.getAddress();
        this.lat = shopRequest.getLat();
        this.lng = shopRequest.getLng();
        this.photo = shopRequest.getPhoto();
        this.contact = shopRequest.getContact();
        this.sns = shopRequest.getSns();
        this.homepage = shopRequest.getHomepage();
        this.flagshipProduct = shopRequest.getFlagshipProduct();
        this.operatingStart = shopRequest.getOperatingStart();
        this.operatingEnd = shopRequest.getOperatingEnd();
        this.holiday = shopRequest.getHoliday();
    }
}
