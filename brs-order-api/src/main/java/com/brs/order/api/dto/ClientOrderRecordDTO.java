package com.brs.order.api.dto;

import com.brs.order.api.Order;
import com.brs.order.api.model.OrderDescription;
import lombok.Data;

import java.sql.Date;

/**
 * @author tiny lin
 * @date 2019/2/27
 */
@Data
public class ClientOrderRecordDTO {
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


    /**
     * 订单状态
     */
    private Object orderStatus="";



    public ClientOrderRecordDTO(){}
    public ClientOrderRecordDTO(OrderDescription orderDescription, Order order){
        this.orderStatus = orderDescription.getOrderStatus();

    }

}
