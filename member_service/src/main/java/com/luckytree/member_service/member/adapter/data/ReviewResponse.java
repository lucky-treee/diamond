package com.luckytree.member_service.member.adapter.data;

import com.luckytree.member_service.common.enums.Category;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponse {

    private String shopName;
    private Category category;
    private String photo;
    private String content;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createAt;
}
