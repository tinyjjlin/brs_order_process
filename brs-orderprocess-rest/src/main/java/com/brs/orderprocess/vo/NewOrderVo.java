package com.brs.orderprocess.vo;

import com.brs.orderinfo.api.Order;
import lombok.Data;

import java.sql.Date;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
@Data
public class NewOrderVo implements Order {
    /**
     * 生成的订单号
     */
    private String orderId;
    /**
     * 订单创建者
     */
    private String creator;

    /**
     * 第三方订单号
     */
    private String thirdId;
    /**
     * 订单类型
     */
    private String orderType;
    /**
     * 影响因子
     */
    private Double impactFactor;
    /**
     * JCR分区
     */
    private String jcr;
    /**
     * 中科院分区
     */
    private String academyOfScienceCn;
    /**
     * 截至日期
     */
    private Date deadline;
    /**
     * 标题
     */
    private String title;
    /**
     * 订单描述
     */
    private String description;
    /**
     * 定金
     */
    private Double deposit;
    /**
     * 总金额
     */
    private Double totalPrice;
    /**
     * 关键字
     */
    private String keyWord;
}
