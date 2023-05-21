package com.luckytree.shop.shop.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;
import luckytree.poom.core.enums.ShopStatus;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class Shop {

    private Long id;
    private String name;
    private ShopCategory category;
    private ShopStatus status;
    private String address;
    private double lat;
    private double lng;
    private ShopHashtag hashtag;
    private String photo;
    private String contact;
    private String sns;
    private String homepage;
    private String flagshipProduct;
    private LocalTime operatingStart;
    private LocalTime operatingEnd;
    private String holiday;

    public Shop(String name, ShopCategory category, ShopStatus status,
                String address, double lat, double lng,
                String photo, String contact, String sns,
                String homepage, String flagshipProduct,
                LocalTime operatingStart, LocalTime operatingEnd, String holiday) {
        this.name = name;
        this.category = category;
        this.status = status;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.photo = photo;
        this.contact = contact;
        this.sns = sns;
        this.homepage = homepage;
        this.flagshipProduct = flagshipProduct;
        this.operatingStart = operatingStart;
        this.operatingEnd = operatingEnd;
        this.holiday = holiday;
    }
}
