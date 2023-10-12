package com.luckytree.shop.shop.adapter.data.shop;

import com.luckytree.shop.shop.domain.shop.Shop;
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
public class ShopDetailResponse {

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

    @Schema(description = "샵 SNS")
    private String sns;

    @Schema(description = "샵 위도")
    private Double lat;

    @Schema(description = "샵 경도")
    private Double lng;

    @Schema(description = "샵 운영 시작시간")
    private String operatingStart;

    @Schema(description = "샵 운영 종료시간")
    private String operationEnd;

    @Schema(description = "샵 휴무일")
    private String holiday;

    public ShopDetailResponse(Shop shop) {
        this.shopName = shop.getName();
        this.category = shop.getCategory();
        this.hashTag = shop.getHashtag();
        this.shopAddress = shop.getAddress();
        this.photo = shop.getPhoto();
        this.contact = shop.getContact();
        this.homepage = shop.getHomepage();
        this.sns = shop.getSns();
        this.lat = shop.getLat();
        this.lng = shop.getLng();
        this.operatingStart = shop.getOperatingStart().toString();
        this.operationEnd = shop.getOperatingEnd().toString();
        this.holiday = shop.getHoliday();
    }
}
