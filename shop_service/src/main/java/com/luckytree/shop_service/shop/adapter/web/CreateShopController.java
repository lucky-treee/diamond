package com.luckytree.shop_service.shop.adapter.web;

import com.luckytree.shop_service.shop.application.port.incoming.CreateShopUseCase;
import com.luckytree.shop_service.shop.adapter.data.ShopRequest;
import com.luckytree.shop_service.common.dto.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "샵 등록", description = "샵 등록 API 모음")
@RestController
@RequestMapping("/shop/create")
@RequiredArgsConstructor
public class CreateShopController {

    private final CreateShopUseCase createShopUseCase;

    @Operation(summary = "샵 등록요청 API")
    @PostMapping("/request")
    @Deprecated
    public ResultResponse requestShopRegistration(@RequestBody @Valid ShopRequest shopRequest) {
        createShopUseCase.requestShopRegistration(shopRequest);
        return new ResultResponse<>(HttpStatus.OK);
    }
}
