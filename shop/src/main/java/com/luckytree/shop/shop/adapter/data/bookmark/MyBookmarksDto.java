package com.luckytree.shop.shop.adapter.data.bookmark;

import com.luckytree.shop.shop.adapter.data.bookmark.BookmarkDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyBookmarksDto {

    private List<BookmarkDto> bookmarks;
}
