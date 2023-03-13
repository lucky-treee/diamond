package com.luckytree.member_service.member.adapter.data;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ShopDetailDto {

    private String shopName;
    private String category;
    private Hashtag hashTag;
    private String shopAddress;
    private String photo;
    private String contact;
    private String homepage;
    private String flagshipProduct;
    private String sns;
    private Double lat;
    private Double lng;
    private String operatingStart;
    private String operationEnd;
    private String holiday;
}
