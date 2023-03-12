package com.luckytree.shop_service.shop.domain;

public enum Hashtag implements EnumModel {
    GOOD("GOOD"),
    CLEAN("CLEAN"),
    NICE("NICE"),
    CHEAP("CHEAP"),
    QUALIT("QUALIT");

    private final String value;

    Hashtag(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public  String getValue() {
        return value;
    }
}
