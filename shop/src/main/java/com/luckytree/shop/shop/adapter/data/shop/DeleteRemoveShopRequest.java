package com.luckytree.shop.shop.adapter.data.shop;

import com.luckytree.shop.shop.domain.shop.ShopRemoveDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "샵 삭제 요청")
@NoArgsConstructor
public class DeleteRemoveShopRequest {

    private Long shopId;

    @NotBlank
    @Schema(description = "샵 삭제사유")
    private String comment;

    public ShopRemoveDetail toDomain() {
        return new ShopRemoveDetail(shopId, comment);
    }
}
