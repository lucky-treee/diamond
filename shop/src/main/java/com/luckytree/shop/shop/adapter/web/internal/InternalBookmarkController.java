package com.luckytree.shop.shop.adapter.web.internal;

import com.luckytree.shop.shop.adapter.data.MyBookmarksDto;
import com.luckytree.shop.shop.adapter.data.ShopFeignResponseDto;
import com.luckytree.shop.shop.application.port.incoming.ShopUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Internal API", description = "호출 X")
@RestController
@RequestMapping("/v1/bookmarks")
@RequiredArgsConstructor
public class InternalBookmarkController {

    private final ShopUseCase shopUseCase;

    @PostMapping("/shops")
    public MyBookmarksDto findShopsByIds(@RequestBody ShopFeignResponseDto shopFeignResponseDto) {
        return shopUseCase.findMyBookmarksDtoByIds(shopFeignResponseDto.getShopIds());
    }
}
