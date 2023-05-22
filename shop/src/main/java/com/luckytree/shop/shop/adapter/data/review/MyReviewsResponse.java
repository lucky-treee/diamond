package com.luckytree.shop.shop.adapter.data.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyReviewsResponse {

    private List<ReviewResponse> reviews;
}
