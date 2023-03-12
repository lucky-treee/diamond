package com.luckytree.shop_service.shop.adapter.persistence;

import com.luckytree.shop_service.common.dto.EnumValue;
import com.luckytree.shop_service.shop.domain.EnumModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnumMapper {
    private final Map<String, List<EnumValue>> factory = new HashMap<>();

    private List<EnumValue> toEnumValues(Class<? extends EnumModel> e) {
        return Arrays.stream(e.getEnumConstants()).map(EnumValue::new).collect(Collectors.toList());
    }

    public void put(String key, Class<? extends EnumModel> e) {
        factory.put(key, toEnumValues(e));
    }

}