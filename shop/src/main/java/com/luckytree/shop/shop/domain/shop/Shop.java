package com.luckytree.shop.shop.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;
import luckytree.poom.core.enums.ShopStatus;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Pattern;

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
    private LocalTime operatingStart;
    private LocalTime operatingEnd;
    private String holiday;
    private LocalDateTime updateAt;
    private LocalDateTime createAt;

    public Shop(String name, ShopCategory category, ShopStatus status,
                String address, double lat, double lng,
                String photo, String contact, String sns,
                String homepage,
                LocalTime operatingStart, LocalTime operatingEnd, String holiday, LocalDateTime updateAt, LocalDateTime createAt) {
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
        this.operatingStart = operatingStart;
        this.operatingEnd = operatingEnd;
        this.holiday = holiday;
        this.updateAt = updateAt;
        this.createAt = createAt;
    }

    public Boolean valid(Shop shop) {
        if (!(Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$", shop.getContact()))) {
            return false;
        }
        if (shop.getLng() < 124 || shop.getLng() > 132) {
            return false;
        }
        if (shop.getLat() < 33 || shop.getLat() > 43) {
            return false;
        }
        return true;
    }
}
