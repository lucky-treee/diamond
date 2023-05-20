package com.luckytree.shop_service.shop.adapter.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "샵 리뷰 사진 수정 정보 DTO")
public class UpdateReviewPhotoDto {

    @NotBlank
    @Schema(description = "리뷰 아이디")
    @Size(max = 50)
    private long reviewId;

    @NotBlank
    @Schema(description = "리뷰 사진")
    private List<MultipartFile> reviewPhoto;
}