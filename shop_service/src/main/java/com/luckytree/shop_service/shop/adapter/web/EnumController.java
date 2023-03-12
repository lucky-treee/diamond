package com.luckytree.shop_service.shop.adapter.web;


import com.luckytree.shop_service.common.dto.EnumValue;
import com.luckytree.shop_service.shop.domain.EnumModel;
import com.luckytree.shop_service.shop.domain.Hashtag;
import com.luckytree.shop_service.shop.domain.ShopStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Enum", description = "Enum 객체 Value 설명 ")
@RestController
public class EnumController {

    @GetMapping("/value")
    public Map<String, List<EnumValue>> getEnumValue() {
        Map<String, List<EnumValue>> enumValues = new LinkedHashMap<>();

        enumValues.put("hashTag", toEnumValue(Hashtag.class));
        enumValues.put("shopStatus", toEnumValue(ShopStatus.class));

        return enumValues;
    }

    private List<EnumValue> toEnumValue(Class<? extends EnumModel> e) {
        return Arrays.stream(e.getEnumConstants()).map(EnumValue::new).collect(Collectors.toList());
    }

}