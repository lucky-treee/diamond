package com.luckytree.shop.shop.domain.review;

import com.luckytree.shop.shop.domain.Page;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PagedReview {
    private Page page;
    private List<Review> reviews;
}
