package com.luckytree.member.member.adapter.data;

import com.luckytree.member.common.enums.Category;
import com.luckytree.member.common.enums.Hashtag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkResponse {

    private String name;
    private Hashtag hashtag;
    private String photo;
    private String address;
    private Category category;
}
