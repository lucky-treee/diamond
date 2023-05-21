package com.luckytree.member.member.adapter.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookmarksResponse {

    private List<BookmarkResponse> bookmarks;
}
