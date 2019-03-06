package com.brs.orderinfo.service;

import com.brs.orderinfo.api.ArticleService;
import com.brs.orderinfo.api.domain.ArticleDomain;
import com.brs.orderinfo.persistence.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public void createArticle(ArticleDomain newArticle) {
          articleDao.insert(newArticle);
    }

    @Override
    public List<ArticleDomain> getArticleListByUserId(String userId) {
        return articleDao.selectList(userId);
    }
}
