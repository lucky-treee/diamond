package com.luckytree.shop.shop.adapter.data.bookmark;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ShopFeignResponse {

    private List<BookmarkResponse> bookmarks;
}
