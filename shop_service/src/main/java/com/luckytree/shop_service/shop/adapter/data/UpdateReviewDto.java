package com.luckytree.shop_service.shop.adapter.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.shop.domain.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "샵 리뷰 수정 정보 DTO")
public class UpdateReviewDto {

    //@NotBlank
    @Schema(description = "리뷰 아이디")
    //@Size(max = 50)
    private long reviewId;

    @NotBlank
    @Schema(description = "리뷰 내용")
    private String content;

    @Schema(description = "해시태그")
    private Hashtag hashtag;
}