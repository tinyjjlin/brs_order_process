package com.brs.orderinfo.service;

import com.brs.orderinfo.api.AuthorService;
import com.brs.orderinfo.api.domain.ArticleAuthorDomain;
import com.brs.orderinfo.persistence.dao.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
@Service
public class AuthorSerivceImpl implements AuthorService {
    @Autowired
    private AuthorDao authorDao;

    @Override
    public void addAuthor(String orderId, ArticleAuthorDomain author) {
          authorDao.insert(orderId,author );
    }

    @Override
    public void addAuthor(String orderId, List<ArticleAuthorDomain> authorList) {
        authorDao.insertMulti(orderId,authorList );
    }

    @Override
    public List <ArticleAuthorDomain> getAuthorList(String orderId) {
        return  authorDao.selectList(orderId);
    }
}
