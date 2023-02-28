package com.luckytree.shop_service.shop.adapter.web;

import com.luckytree.shop_service.common.dto.ResultResponse;
import com.luckytree.shop_service.shop.application.port.incoming.RemoveRequestForm;
import com.luckytree.shop_service.shop.application.port.incoming.RemoveShopUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "샵 삭제", description = "샵 삭제 관련 API 모음")
@RestController
@RequestMapping("/shop/remove")
@RequiredArgsConstructor
public class RemoveShopController {

    private final RemoveShopUseCase removeShopUseCase;

    @Operation(summary = "샵 삭제요청 API")
    @PostMapping("/request")
    @Deprecated
    public ResultResponse removeShopRequest(@RequestBody @Valid RemoveRequestForm removeRequestForm) {
        removeShopUseCase.removeShopRequest(removeRequestForm);
        return new ResultResponse<>(HttpStatus.OK);
    }
}
