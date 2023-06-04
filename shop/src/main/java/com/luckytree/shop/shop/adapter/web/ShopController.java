package com.luckytree.shop.shop.adapter.web;

import com.luckytree.shop.shop.adapter.data.bookmark.BookmarkResponse;
import com.luckytree.shop.shop.adapter.data.bookmark.ShopFeignResponse;
import com.luckytree.shop.shop.adapter.data.shop.*;
import com.luckytree.shop.shop.application.port.incoming.ShopUseCase;
import com.luckytree.shop.shop.domain.shop.Shop;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Shop", description = "가게")
@RestController
@RequestMapping("v1/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopUseCase shopUseCase;

    @Operation(summary = "샵 등록요청")
    @PostMapping
    public ResponseEntity<Object> createShop(@RequestBody @Valid CreateShopRequest createShopRequest) {
        shopUseCase.create(createShopRequest.toDomain());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "샵 조회")
    @GetMapping("{id}")
    public ResponseEntity<ShopDetailResponse> getById(@PathVariable("id") Long id) {
        Shop shop = shopUseCase.getById(id);
        return ResponseEntity.ok(new ShopDetailResponse(shop));
    }

    @Operation(summary = "샵 목록 조회")
    @GetMapping
    public ResponseEntity<List<SearchShopsResponse>> getShops(SearchShopsRequest searchShopsRequest) {
        List<SearchShopsResponse> searchShopsResponses = shopUseCase.getShops(searchShopsRequest.toSearchShopsCondition()).stream().map(SearchShopsResponse::new).toList();

        return ResponseEntity.ok(searchShopsResponses);
    }

    @Operation(summary = "샵 삭제요청")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteShop(@RequestBody @Valid DeleteRemoveShopRequest deleteRemoveShopRequest) {
        shopUseCase.delete(deleteRemoveShopRequest.toDomain());
    }

    @PostMapping("bookmarks")
    public ShopFeignResponse getShopsByIds(@RequestBody ShopFeignRequest shopFeignRequest) {
        List<Shop> shops = shopUseCase.getShopsByIds(shopFeignRequest.getShopIds());

        return new ShopFeignResponse(shops.stream().map(BookmarkResponse::new).toList());
    }
}