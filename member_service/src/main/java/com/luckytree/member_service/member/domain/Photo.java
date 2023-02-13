package com.luckytree.member_service.member.domain;

public enum Photo {
    DEFAULT("temp1"), BOY("temp2"), GIRL("temp3");

    final private String url;

    Photo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
