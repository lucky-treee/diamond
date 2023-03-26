package com.luckytree.member_service.member.adapter.data;

import com.luckytree.member_service.common.enums.Category;
import com.luckytree.member_service.common.enums.Hashtag;
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
