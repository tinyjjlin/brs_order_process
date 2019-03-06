package com.brs.orderinfo.persistence.dao;

import com.brs.orderinfo.api.domain.ArticleDomain;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
public interface ArticleDao {
    /**
     * 添加新的文章
     * @param newArticle
     */
    @Insert("")
    void insert(@Param("newArticle") ArticleDomain newArticle);

    /**
     * 查看文章列表
     * @param userId
     * @return
     */
    List<ArticleDomain> selectList(@Param("userId") String userId);
}
