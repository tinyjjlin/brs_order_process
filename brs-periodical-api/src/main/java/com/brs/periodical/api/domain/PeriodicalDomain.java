package com.brs.periodical.api.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 期刊业务实体
 * @author tiny lin
 * @date 2019/3/1
 */
@Data
public class PeriodicalDomain {
    /**
     * 期刊名称
     */
    private String name;
    /**
     * 期刊别名
     */
    private String alias;

    /**
     * ?
     */
    private String  issn;

    /**
     * 期刊影响因子
     */
    private BigDecimal impactFactor;

    /**
     * 命中率
     */
    private BigDecimal hitRate;

    /**
     * 一审周期
     */
    private double firstReviewPeriod;

    /**
     * 出版社
     */
    private String press;

    /**
     * 投稿链接
     */
    private String contributeLink;

    /**
     * 备注
     */
    private String remark;
}
