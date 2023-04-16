package com.luckytree.shop_service.shop.adapter.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luckytree.shop_service.shop.domain.ReviewPhoto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "샵 리뷰 사진 등록 DTO")
public class CreateReviewPhotoDto {

    @NotBlank
    @Schema(description = "리뷰 아이디")
    @Size(max = 50)
    private long reviewId;

    @NotBlank
    @Schema(description = "리뷰 내용")
    private String photoUrl;

    @JsonIgnore
    public ReviewPhoto toDomain() {
        return new ReviewPhoto(
                reviewId,
                photoUrl
        );
    }
}
