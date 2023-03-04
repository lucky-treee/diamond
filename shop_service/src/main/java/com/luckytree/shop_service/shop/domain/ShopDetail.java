package com.luckytree.shop_service.shop.domain;

import com.luckytree.shop_service.shop.adapter.persistence.ShopEntity;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ShopDetail{

    private String shopName;
    private String category;
    private Hashtag hashTag;
    private String shopAddress;
    private String photo;
    private String contact;
    private String homepage;
    private String flagshipProduct;
    private  String sns;
    private  Double lat;
    private  Double lng;
    private String operatingStart;
    private  String operationEnd;
    private String holiday;

    public ShopDetail(ShopEntity shopEntity){
        this.shopName = shopEntity.getName();
        this.category = shopEntity.getCategory();
        this.hashTag = shopEntity.getHashtag();
        this.shopAddress = shopEntity.getAddress();
        this.photo = shopEntity.getPhoto();
        this.contact = shopEntity.getContact();
        this.homepage = shopEntity.getHomepage();
        this.flagshipProduct = shopEntity.getFlagshipProduct();
        this.sns = shopEntity.getSns();
        this.lat = shopEntity.getLat();
        this.lng = shopEntity.getLng();
        this.operatingStart = shopEntity.getOperatingStart().toString();
        this.operationEnd = shopEntity.getOperatingEnd().toString();
        this.holiday = shopEntity.getHoliday();
    }
}
