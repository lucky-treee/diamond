package com.luckytree.member_service.member.domain;

import com.luckytree.member_service.common.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Bookmark {

    private long memberId;
    private long shopId;
    private Category category;

    public Bookmark(long memberId, long shopId, Category category) {
        this.memberId = memberId;
        this.shopId = shopId;
        this.category = category;
    }
}