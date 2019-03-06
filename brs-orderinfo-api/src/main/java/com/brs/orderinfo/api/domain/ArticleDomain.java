package com.brs.orderinfo.api.domain;

import lombok.Data;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
@Data
public class ArticleDomain {
    private String artId;
    private String artTitle;
    private String orderId;
    private String remark;
    private String artStatus;
    private String minIf;
    private String maxIf;

}
