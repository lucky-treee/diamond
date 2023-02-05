package com.luckytree.shop_service.shop.adapter.in.web;

import com.luckytree.shop_service.common.dto.ResultResponse;
import com.luckytree.shop_service.shop.adapter.out.persistence.ShopEntity;
import com.luckytree.shop_service.shop.adapter.out.persistence.ShopTempEntity;
import com.luckytree.shop_service.shop.application.port.in.GetShopListUseCase;
import com.luckytree.shop_service.shop.domain.Hashtag;
import com.luckytree.shop_service.shop.domain.ShopDetail;
import com.luckytree.shop_service.shop.domain.ShopSummary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "샵 조회", description = "샵 조회 API 모음")
@RestController
@RequestMapping("/shop/get")
@RequiredArgsConstructor
public class GetShopListController {

    private final GetShopListUseCase getShopListUseCase;

    @Operation(summary = "특정 카테고리의 샵 전체 검색")
    @GetMapping("/{category}")
    public ResultResponse getShopListByCategory(@PathVariable("category") String category) {
        List<ShopSummary> shopSummaryList = getShopListUseCase.getShopSummaryByCategory(category);
        return new ResultResponse<>(shopSummaryList);
    }

    @Operation(summary = "범위 내 샵 전체 검색")
    @GetMapping("/{maxLat}/{minLat}/{maxLng}/{minLng}")
    public ResultResponse getShopAll(@PathVariable("maxLat") double maxLat, @PathVariable("minLat") double minLat, @PathVariable("maxLng") double maxLng, @PathVariable("minLng") double minLng) {
        List<ShopSummary> shopSummary = getShopListUseCase.getShopAll(maxLat, minLat, maxLng, minLng);
        return new ResultResponse<>(shopSummary);
    }

    @Operation(summary = "선택된 샵 상세정보 검색")
    @GetMapping("/detail/{name}/{address}")
    public ResultResponse getShopDetail(@PathVariable("name") String name, @PathVariable("address") String address) {
        ShopDetail shopDetail = getShopListUseCase.getShopDetail(name, address);
        return new ResultResponse<>(shopDetail);
    }

    @Operation(summary = "특정 해쉬태크의 샵 상세정보 조회")
    @GetMapping("hashtag/{hashtag}")
    public ResultResponse getShopSummaryByHashtag(@PathVariable("hashtag") Hashtag hashtag) {
        List<ShopSummary> shopSummaryList = getShopListUseCase.getShopSummaryByHashtag(hashtag);
        return new ResultResponse<>(shopSummaryList);
    }
}
