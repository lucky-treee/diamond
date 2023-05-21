package com.luckytree.shop.shop.adapter.data.bookmark;

import com.luckytree.shop.shop.adapter.jpa.shop.ShopEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkDto {

    private String name;
    private ShopHashtag hashtag;
    private String photo;
    private String address;
    private ShopCategory category;

    public BookmarkDto(ShopEntity shopEntity) {
        this.name = shopEntity.getName();
        this.hashtag = shopEntity.getHashtag();
        this.photo = shopEntity.getPhoto();
        this.address = shopEntity.getAddress();
        this.category = shopEntity.getCategory();
    }
}