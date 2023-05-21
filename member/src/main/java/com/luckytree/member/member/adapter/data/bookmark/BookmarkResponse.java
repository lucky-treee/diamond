package com.luckytree.member.member.adapter.data.bookmark;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkResponse {

    private String name;
    private ShopHashtag hashtag;
    private String photo;
    private String address;
    private ShopCategory category;
}
