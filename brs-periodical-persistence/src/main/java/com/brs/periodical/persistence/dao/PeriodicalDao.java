package com.brs.periodical.persistence.dao;

import com.brs.periodical.api.domain.PeriodicalDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
@Mapper
public interface PeriodicalDao {

    @Select("select name_ as name,alias_ as alias,issn_ as issn , impact_factor_ as impactFactor, hit_rate_ as hitRate," +
            "first_review_period_ as firstReviewPeriod , press_ as press,contribute_link_ as contributeLink,remark_ as remark from brs_periodical")
    List<PeriodicalDomain> selectList();
}
