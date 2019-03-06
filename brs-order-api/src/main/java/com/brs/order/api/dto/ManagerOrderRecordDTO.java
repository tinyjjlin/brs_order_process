package com.brs.order.api.dto;

import com.brs.order.api.Order;
import com.brs.order.api.model.OrderDescription;
import lombok.Data;
import sun.security.x509.EDIPartyName;

import java.sql.Date;

/**
 * @author tiny lin
 * @date 2019/2/27
 */
@Data
public class ManagerOrderRecordDTO {
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

//======================from process variable==================
    /**
     * 编辑
     */
    private Object editor="";
    /**
     * 数据处理人员
     */
    private Object dataProcessor="";
    /**
     * 投稿人员
     */
    private Object submitter="";
    /**
     * 订单状态
     */
    private Object orderStatus="";
    /**
     * 用户任务Id
     */
    private String taskId="";

    public ManagerOrderRecordDTO(){}
    public ManagerOrderRecordDTO(OrderDescription orderDescription, Order order,String taskId){
        this.editor = orderDescription.getEditor();
        this.dataProcessor = orderDescription.getDataProcessor();
        this.submitter = orderDescription.getSubmitter();
        this.orderStatus = orderDescription.getOrderStatus();

        this.taskId = taskId;

    }

}
