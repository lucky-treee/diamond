package com.luckytree.shop_service.shop.adapter.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.luckytree.shop_service.common.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "샵 등록 DTO")
public class ShopRequest {

    @NotBlank
    @Schema(description = "샵 이름")
    @Size(max = 50)
    private String shopName;

    @Schema(description = "샵 카테고리")
    private Category category;

    @NotBlank
    @Schema(description = "샵 주소")
    @Size(max = 50)
    private String address;

    @Schema(description = "샵 사진")
    @Size(max = 150)
    private String photo;

    @Schema(description = "샵 전화번호")
    @Size(max = 50)
    private String contact;

    @Schema(description = "샵 홈페이지")
    @Size(max = 50)
    private String homepage;

    @Schema(description = "샵 대표상품")
    @Size(max = 50)
    private String flagshipProduct;

    @Schema(description = "샵 SNS")
    @Size(max = 50)
    private String sns;

    @NotNull
    @Schema(description = "샵 위도")
    private double lat;

    @NotNull
    @Schema(description = "샵 경도")
    private double lng;

    @Schema(description = "샵 영업 시작시간")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime operatingStart;

    @Schema(description = "샵 영업 종료시간")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime operatingEnd;

    @Schema(description = "샵 휴무일")
    @Size(max = 20)
    private String holiday;
}
