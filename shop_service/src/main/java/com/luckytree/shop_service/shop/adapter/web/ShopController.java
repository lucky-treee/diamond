package com.luckytree.shop_service.shop.adapter.web;

import com.luckytree.shop_service.shop.adapter.data.ShopRequest;
import com.luckytree.shop_service.shop.application.port.incoming.RemoveRequestForm;
import com.luckytree.shop_service.shop.application.port.incoming.ShopUseCase;
import com.luckytree.shop_service.shop.domain.Hashtag;
import com.luckytree.shop_service.shop.domain.ShopDetail;
import com.luckytree.shop_service.shop.domain.ShopSummary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    private final ShopUseCase shopUseCase;

    @Operation(summary = "샵 등록요청 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "샵 등록 완료",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShopRequest.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @PostMapping("/create/request")
    public ResponseEntity<Object> requestShopRegistration(@RequestBody @Valid ShopRequest shopRequest) {
        shopUseCase.requestShopRegistration(shopRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "특정 카테고리의 샵 전체 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "샵 조회 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShopSummary.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @GetMapping("/get/category")
    public ResponseEntity<List<ShopSummary>> getShopListByCategory(@RequestParam String category) {
        List<ShopSummary> shopSummaryList = shopUseCase.getShopSummaryByCategory(category);
        return new ResponseEntity<>(shopSummaryList, HttpStatus.OK);
    }

    @Operation(summary = "범위 내 샵 전체 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "샵 조회 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShopSummary.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @GetMapping("/get")
    public ResponseEntity<List<ShopSummary>> getShopAll(@RequestParam double maxLat, @RequestParam double minLat, @RequestParam double maxLng, @RequestParam double minLng) {
        List<ShopSummary> shopSummary = shopUseCase.getShopAll(maxLat, minLat, maxLng, minLng);
        return new ResponseEntity<>(shopSummary, HttpStatus.OK);
    }

    @Operation(summary = "선택된 샵 상세정보 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "샵 조회 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShopDetail.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @GetMapping("/get/detail")
    public ResponseEntity<ShopDetail> getShopDetail(@RequestParam String name, @RequestParam String address) {
        ShopDetail shopDetail = shopUseCase.getShopDetail(name, address);
        return new ResponseEntity<>(shopDetail, HttpStatus.OK);
    }

    @Operation(summary = "특정 해쉬태크의 샵 상세정보 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "샵 조회 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShopSummary.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @GetMapping("/get/hashtag")
    public ResponseEntity<List<ShopSummary>> getShopSummaryByHashtag(@RequestParam Hashtag hashtag) {
        List<ShopSummary> shopSummaryList = shopUseCase.getShopSummaryByHashtag(hashtag);
        return new ResponseEntity<>(shopSummaryList, HttpStatus.OK);
    }

    @Operation(summary = "샵 삭제요청 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "샵 삭제요청 완료",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)})
    @PostMapping("/remove/request")
    public ResponseEntity<Object> removeShopRequest(@RequestBody @Valid RemoveRequestForm removeRequestForm) {
        shopUseCase.removeShopRequest(removeRequestForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
