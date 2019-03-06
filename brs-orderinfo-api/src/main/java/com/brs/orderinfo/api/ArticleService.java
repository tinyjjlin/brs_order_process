package com.brs.orderinfo.api;

import com.brs.orderinfo.api.domain.ArticleDomain;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
public interface ArticleService {
    /**
     * 创建新的文章
     * @param newArticle
     */
    void createArticle(ArticleDomain newArticle);

    /**
     * 根据用户id ,查看文章列表
     * @param userId
     * @return
     */
    List<ArticleDomain> getArticleListByUserId(String userId);
}
