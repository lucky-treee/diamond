package com.luckytree.member_service.member.domain;

import com.luckytree.member_service.member.adapter.data.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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