package com.luckytree.member_service.member.adapter.data;

import com.luckytree.member_service.common.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponse {

    private String shopName;
    private Category category;
    private List<String> photoUrl;
    private String content;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createAt;
}
