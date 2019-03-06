package com.brs.orderinfo.persistence.dao;

import com.brs.orderinfo.api.domain.ArticleAuthorDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
public interface AuthorDao {

    void insert(@Param("orderId") String orderId,@Param("author") ArticleAuthorDomain author);

    void insertMulti(@Param("orderId") String orderId,@Param("authorList") List<ArticleAuthorDomain> authorList);

    List<ArticleAuthorDomain> selectList(@Param("orderId") String orderId);
}
