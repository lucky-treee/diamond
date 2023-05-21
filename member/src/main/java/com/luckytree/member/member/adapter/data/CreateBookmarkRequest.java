package com.luckytree.member.member.adapter.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luckytree.poom.core.enums.ShopCategory;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "즐겨찾기 등록 DTO")
public class CreateBookmarkRequest {

    @Schema(description = "멤버 id")
    private long memberId;

    @Schema(description = "샵 id")
    private long shopId;

    @Schema(description = "샵 카테고리")
    private ShopCategory category;
}
