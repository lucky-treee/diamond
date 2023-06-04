package com.luckytree.shop.shop.adapter.data.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luckytree.shop.shop.domain.review.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luckytree.poom.core.enums.ShopHashtag;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "샵 리뷰 등록 DTO")
public class CreateReviewRequest {

    @NotNull
    private Long shopId;

    @NotBlank
    @Schema(description = "리뷰 내용")
    private String content;

    private ShopHashtag hashtag;

    @JsonIgnore
    public Review toDomain(Long memberId) {
        return new Review(
                shopId,
                memberId,
                content,
                hashtag
        );
    }
}
