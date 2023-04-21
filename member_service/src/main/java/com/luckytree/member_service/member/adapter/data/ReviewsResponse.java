package com.luckytree.member_service.member.adapter.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReviewsResponse {

    private List<ReviewResponse> reviews;
}
