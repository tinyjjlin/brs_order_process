package com.brs.orderinfo.api;

import com.brs.orderinfo.api.domain.ArticleAuthorDomain;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
public interface AuthorService {

    /**
     * 添加文章作者
     * @param orderId
     * @param author
     */
    void addAuthor(String orderId,ArticleAuthorDomain author);

    /**
     * 一次添加多个文章作者信息
     * @param orderId
     * @param authorList
     */
    void addAuthor(String orderId,List<ArticleAuthorDomain> authorList);

    /**
     * 根据用户id,获取文章作者信息
     * @param orderId
     * @return
     */
    List<ArticleAuthorDomain> getAuthorList(String orderId);
}
