package com.luckytree.shop_service.common.dto;

import com.luckytree.shop_service.shop.domain.EnumModel;
import lombok.Getter;

@Getter
public class EnumValue {
    private final String key;
    private final String value;

    public EnumValue(EnumModel enumModel) {
        key = enumModel.getKey();
        value = enumModel.getValue();
    }
}