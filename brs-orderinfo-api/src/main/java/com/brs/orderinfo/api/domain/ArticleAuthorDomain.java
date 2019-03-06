package com.brs.orderinfo.api.domain;

import lombok.Data;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
@Data
public class ArticleAuthorDomain {
    private String authorId;
    private String firstName;
    private String lastName;
    /**
     * 头衔
     */
    private String honor;
    private String phone;
    private String email;
    private String orderId;
    /**
     * 作者排序
     */
    private String authorSort;
}
