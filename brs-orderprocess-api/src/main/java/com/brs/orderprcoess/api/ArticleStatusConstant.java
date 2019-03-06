package com.brs.orderprcoess.api;

/**
 * 文章状态
 * @author tiny lin
 * @date 2019/3/5
 */
public interface ArticleStatusConstant {
    String ARTICLE_STATUS_NEW = "新建";
    String ARTICLE_STATUS_DRAFT= "草稿";
    String ARTICLE_STATUS_MODIFY="修改";
    String ARTICLE_STATUS_COMPLETE="撰写完成";
    String ARTICLE_STATUS_DISPATCH_DATA="实验转发";
    String ARTICLE_STATUS_COMPLETE_DATA="完成实验";
    String ARTICLE_STATUS_CONTRIBUTE="已投稿";
}
