package com.luckytree.shop.shop.adapter.data.shop;

import com.luckytree.shop.shop.adapter.jpa.shop.ShopEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "샵 상세정보")
@EqualsAndHashCode(callSuper = false)
@ToString
public class ShopDetailDto {

    @Schema(description = "샵 이름")
    private String shopName;

    @Schema(description = "샵 카테고리")
    private ShopCategory category;

    @Schema(description = "샵 해시태그")
    private ShopHashtag hashTag;

    @Schema(description = "샵 주소")
    private String shopAddress;

    @Schema(description = "샵 사진")
    private String photo;

    @Schema(description = "샵 전화번호")
    private String contact;

    @Schema(description = "샵 홈페이지")
    private String homepage;

    @Schema(description = "샵 대표상품")
    private String flagshipProduct;

    @Schema(description = "샵 SNS")
    private  String sns;

    @Schema(description = "샵 위도")
    private  Double lat;

    @Schema(description = "샵 경도")
    private  Double lng;

    @Schema(description = "샵 운영 시작시간")
    private String operatingStart;

    @Schema(description = "샵 운영 종료시간")
    private  String operationEnd;

    @Schema(description = "샵 휴무일")
    private String holiday;

    public ShopDetailDto(ShopEntity shopEntity){
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
