package com.luckytree.shop_service.shop.domain;

public enum ShopStatus implements EnumModel {
    ENABLE("ENABLE"),
    DISABLE("DISABLE");

    private final String value;

    ShopStatus(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
