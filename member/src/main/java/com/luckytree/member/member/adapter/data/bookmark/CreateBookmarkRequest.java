package com.luckytree.member.member.adapter.data.bookmark;

import com.luckytree.member.member.domain.bookmark.Bookmark;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luckytree.poom.core.enums.ShopCategory;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "즐겨찾기 등록 요청")
public class CreateBookmarkRequest {
    @NotNull
    private long shopId;

    @Schema(description = "샵 카테고리")
    private ShopCategory category;

    public Bookmark toDomain(Long memberId) {
        return new Bookmark(memberId, shopId, category);
    }
}
