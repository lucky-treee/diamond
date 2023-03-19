package com.luckytree.member_service.member.adapter.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luckytree.member_service.member.domain.Bookmark;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "즐겨찾기 등록 DTO")
public class CreateBookmarkDto {

    @Schema(description = "멤버 id")
    @Size(max = 20)
    private long memberId;

    @Schema(description = "샵 id")
    @Size(max = 20)
    private long shopId;

    @Schema(description = "샵 카테고리")
    private Category category;

    public CreateBookmarkDto(MemberFeignRequestDto memberFeignRequestDto) {
        this.memberId = memberFeignRequestDto.getMemberId();
        this.shopId = memberFeignRequestDto.getShopId();
        this.category = memberFeignRequestDto.getCategory();
    }

    @JsonIgnore
    public Bookmark toDomain() {
        return new Bookmark(
                memberId,
                shopId,
                category
        );
    }
}