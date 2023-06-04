package com.luckytree.shop.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
public class Page {
    private Long total;
    private Long limit;
    private Long offset;
    private Sort sort = Sort.by("id").descending();

    public Page(Long total, Long limit, Long offset) {
        this.total = total;
        this.limit = limit;
        this.offset = offset;
    }

    public PageRequest toPageRequest() {
        return PageRequest.of(offset.intValue(), limit.intValue(), sort);
    }
}
