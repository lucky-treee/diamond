package com.luckytree.shop_service.shop.domain;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.common.enums.ShopStatus;

import java.time.LocalTime;

public class Shop {

    private Long id;
    private String name;
    private Category category;
    private ShopStatus status;
    private String address;
    private double lat;
    private double lng;
    private Hashtag hashtag;
    private String photo;
    private String contact;
    private String sns;
    private String homepage;
    private String flagshipProduct;
    private LocalTime operatingStart;
    private LocalTime operatingEnd;
    private String holiday;
}
