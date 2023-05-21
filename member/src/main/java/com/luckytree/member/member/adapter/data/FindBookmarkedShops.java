package com.luckytree.member.member.adapter.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FindBookmarkedShops {

    private final List<Long> shopIds;
}
