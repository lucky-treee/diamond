package com.luckytree.shop.shop.adapter.data.bookmark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyBookmarksResponse {

    private List<BookmarkResponse> bookmarks;
}
