package com.luckytree.shop.shop.adapter.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "샵 리뷰 사진 등록 DTO")
public class CreateReviewPhotoDto {

    @Schema(description = "리뷰 아이디")
    private long reviewId;

    @NotNull
    @Schema(description = "리뷰 사진")
    private List<MultipartFile> reviewPhoto;
}