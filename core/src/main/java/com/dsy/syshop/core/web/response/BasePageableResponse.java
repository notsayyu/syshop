package com.dsy.syshop.core.web.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 14:45
 */
@Getter
public class BasePageableResponse<T> {
    private int total;
    private int count;
    private List<T> items;

    public BasePageableResponse(int total, List<T> items) {
        this.total = total;
        this.items = items;
        this.count = items.size();
    }

    public BasePageableResponse(Page<T> page) {
        this((int) page.getTotalElements(), page.getContent());
    }
}
