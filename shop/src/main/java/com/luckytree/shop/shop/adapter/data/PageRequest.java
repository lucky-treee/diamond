package com.luckytree.shop.shop.adapter.data;

import com.luckytree.shop.shop.domain.Page;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageRequest {
    private Long total;
    private Long limit;
    private Long offset;

    public Page toDomain() {
        return new Page(total, limit, offset - 1);
    }
}
