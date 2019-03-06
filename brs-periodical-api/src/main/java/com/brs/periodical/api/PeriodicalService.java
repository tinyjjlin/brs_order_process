package com.brs.periodical.api;

import com.brs.periodical.api.domain.PeriodicalDomain;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
public interface PeriodicalService {
    /**
     * 获取期刊列表
     * @return
     */
    List<PeriodicalDomain> getList();
}
