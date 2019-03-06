package com.brs.orderprcoess.model;

import lombok.Data;

/**
 * @author tiny lin
 * @date 2019/2/26
 */
@Data
public class Paging {
    private int page;
    private int size;
    private long total;
}
