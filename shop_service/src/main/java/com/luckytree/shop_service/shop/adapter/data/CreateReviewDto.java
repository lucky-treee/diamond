package com.luckytree.shop_service.shop.adapter.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luckytree.shop_service.shop.domain.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "샵 리뷰 등록 DTO")
public class CreateReviewDto {

    @NotNull
    @Schema(description = "샵 아이디")
    private long shopId;

    @NotNull
    @Schema(description = "멤버 아이디")
    private long memberId;

    @NotBlank
    @Schema(description = "리뷰 내용")
    private String content;

    @JsonIgnore
    public Review toDomain() {
        return new Review(
                shopId,
                memberId,
                content
        );
    }
}
