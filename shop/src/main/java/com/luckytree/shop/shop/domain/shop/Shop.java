package com.luckytree.shop.shop.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;
import luckytree.poom.core.enums.ShopStatus;

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

    public String valid(Shop shop){
        if(!(Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$", shop.getContact()))){
            return "전화번호가 잘못되었습니다.";
        }
        if(shop.getLng()<124 || shop.getLng()>132){
            return "경도가 잘못되었습니다.";
        }
        if(shop.getLat()<33 || shop.getLat()>43){
            return "위도가 잘못되었습니다.";
        }
        return "success";
    }
}
