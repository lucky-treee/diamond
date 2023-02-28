package com.luckytree.shop_service.shop.adapter.web;

import com.luckytree.shop_service.shop.adapter.data.ShopRequest;
import com.luckytree.shop_service.shop.application.port.incoming.*;
import com.luckytree.shop_service.shop.domain.Hashtag;
import com.luckytree.shop_service.shop.domain.ShopDetail;
import com.luckytree.shop_service.shop.domain.ShopSummary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "샵", description = "샵 전체 API 모음")
@RestController
@RequestMapping("/v1/shop")
@RequiredArgsConstructor
public class ShopController {

    private final CreateShopUseCase createShopUseCase;
    private final GetShopListUseCase getShopListUseCase;
    private final RemoveShopUseCase removeShopUseCase;

    @Operation(summary = "샵 등록요청 API")
    @PostMapping("/create/request")
    public ResponseEntity requestShopRegistration(@RequestBody @Valid ShopRequest shopRequest) {
        createShopUseCase.requestShopRegistration(shopRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "특정 카테고리의 샵 전체 검색")
    @GetMapping("/get/category")
    public ResponseEntity getShopListByCategory(@RequestParam String category) {
        List<ShopSummary> shopSummaryList = getShopListUseCase.getShopSummaryByCategory(category);
        return new ResponseEntity<>(shopSummaryList, HttpStatus.OK);
    }

    @Operation(summary = "범위 내 샵 전체 검색")
    @GetMapping("/get")
    public ResponseEntity getShopAll(@RequestParam double maxLat, @RequestParam double minLat, @RequestParam double maxLng, @RequestParam double minLng) {
        List<ShopSummary> shopSummary = getShopListUseCase.getShopAll(maxLat, minLat, maxLng, minLng);
        return new ResponseEntity<>(shopSummary, HttpStatus.OK);
    }

    @Operation(summary = "선택된 샵 상세정보 검색")
    @GetMapping("/get/detail")
    public ResponseEntity getShopDetail(@RequestParam String name, @RequestParam String address) {
        ShopDetail shopDetail = getShopListUseCase.getShopDetail(name, address);
        return new ResponseEntity<>(shopDetail, HttpStatus.OK);
    }

    @Operation(summary = "특정 해쉬태크의 샵 상세정보 조회")
    @GetMapping("/get/hashtag")
    public ResponseEntity getShopSummaryByHashtag(@RequestParam Hashtag hashtag) {
        List<ShopSummary> shopSummaryList = getShopListUseCase.getShopSummaryByHashtag(hashtag);
        return new ResponseEntity<>(shopSummaryList, HttpStatus.OK);
    }

    @Operation(summary = "샵 삭제요청 API")
    @PostMapping("/remove/request")
    public ResponseEntity removeShopRequest(@RequestBody @Valid RemoveRequestForm removeRequestForm) {
        removeShopUseCase.removeShopRequest(removeRequestForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
