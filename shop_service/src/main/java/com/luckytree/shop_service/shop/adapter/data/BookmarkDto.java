package com.luckytree.shop_service.shop.adapter.data;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.shop.adapter.jpa.ShopEntity;
import com.luckytree.shop_service.common.enums.Hashtag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkDto {

    private String name;
    private Hashtag hashtag;
    private String photo;
    private String address;
    private Category category;

    public BookmarkDto(ShopEntity shopEntity) {
        this.name = shopEntity.getName();
        this.hashtag = shopEntity.getHashtag();
        this.photo = shopEntity.getPhoto();
        this.address = shopEntity.getAddress();
        this.category = shopEntity.getCategory();
    }
}
