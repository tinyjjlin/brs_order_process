package com.brs.periodical.service;

import com.brs.periodical.api.PeriodicalService;
import com.brs.periodical.api.domain.PeriodicalDomain;
import com.brs.periodical.persistence.dao.PeriodicalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
@Service
public class PeriodicalServiceImpl implements PeriodicalService {
    @Autowired(required = false)
   private  PeriodicalDao periodicalDao;
    @Override
    public List<PeriodicalDomain> getList() {
        return periodicalDao.selectList();
    }
}
