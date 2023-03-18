package com.luckytree.shop_service.shop.adapter.web.internal;

import com.luckytree.shop_service.shop.adapter.data.MyBookmarksDto;
import com.luckytree.shop_service.shop.adapter.data.ShopFeignResponseDto;
import com.luckytree.shop_service.shop.application.port.incoming.ShopUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Internal API", description = "호출 X")
@RestController
@RequestMapping("/v1/bookmarks")
@RequiredArgsConstructor
public class InternalBookmarkController {

    private final ShopUseCase shopUseCase;

    @PostMapping("/shops")
    public MyBookmarksDto findShopsByIds(ShopFeignResponseDto shopFeignResponseDto) {
        return shopUseCase.findMyBookmarksDtoByIds(shopFeignResponseDto.getShopIds());
    }
}
