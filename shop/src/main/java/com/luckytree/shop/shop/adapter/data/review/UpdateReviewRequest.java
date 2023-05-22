package com.luckytree.shop.shop.adapter.data.review;

import com.luckytree.shop.shop.domain.review.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luckytree.poom.core.enums.ShopHashtag;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "샵 리뷰 수정 정보 DTO")
public class UpdateReviewRequest {

    //@NotBlank
    @Schema(description = "리뷰 아이디")
    //@Size(max = 50)
    private long reviewId;

    private long shopId;

    private long memberId;

    @NotBlank
    @Schema(description = "리뷰 내용")
    private String content;

    @Schema(description = "해시태그")
    private ShopHashtag hashtag;

    public Review toDomain(){
        return new Review(
                shopId,
                memberId,
                content,
                hashtag
        );
    }
}
