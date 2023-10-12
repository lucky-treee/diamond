package com.luckytree.shop.shop.adapter.data.bookmark;

import com.luckytree.shop.shop.domain.shop.Shop;
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

    public BookmarkResponse(Shop shop) {
        this.name = shop.getName();
        this.hashtag = shop.getHashtag();
        this.photo = shop.getPhoto();
        this.address = shop.getAddress();
        this.category = shop.getCategory();
    }
}
