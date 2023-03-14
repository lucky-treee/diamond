package com.luckytree.shop_service.shop.adapter.web.internal;

import com.luckytree.shop_service.shop.adapter.data.MyBookmarksDto;
import com.luckytree.shop_service.shop.application.port.incoming.ShopUseCase;
import com.luckytree.shop_service.shop.application.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/bookmarks")
@RequiredArgsConstructor
public class InternalBookmarkController {

    private final ShopUseCase shopUseCase;

    @PostMapping("/shops")
    public MyBookmarksDto findShopsByIds(List<Long> shopIds) {
        return shopUseCase.findMyBookmarksDtoByIds(shopIds);
    }
}
