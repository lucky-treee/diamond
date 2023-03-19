package com.luckytree.member_service.member.adapter.data;

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
}
