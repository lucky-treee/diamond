package com.luckytree.member.member.domain.bookmark;

import lombok.Getter;
import lombok.NoArgsConstructor;
import luckytree.poom.core.enums.ShopCategory;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Bookmark {

    private long id;

    private long memberId;

    private long shopId;

    private ShopCategory category;

    private LocalDateTime createAt;

    public Bookmark(long memberId, long shopId, ShopCategory category) {
        this.memberId = memberId;
        this.shopId = shopId;
        this.category = category;
    }

    public Bookmark(long id, long memberId, long shopId, ShopCategory category, LocalDateTime createAt) {
        this.id = id;
        this.memberId = memberId;
        this.shopId = shopId;
        this.category = category;
        this.createAt = createAt;
    }
}
