package com.brs.order.api.model;

import lombok.Data;

/**
 * 流程中变量对应订单描述信息
 * @author tiny lin
 * @date 2019/2/27
 */
@Data
public class OrderDescription {
    private Object orderId="";
    /**
     * 订单状态
     */
    private Object orderStatus="";
    /**
     * 编辑
     */
    private Object editor="未分配";
    /**
     * 数据处理人员
     */
    private Object dataProcessor="未分配";
    /**
     * 投稿人员
     */
    private Object submitter="未分配";

    public OrderDescription(){}
    public OrderDescription(Object orderId,Object orderStatus,Object editor,Object dataProcessor,Object submitter){
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.editor = editor;
        this.dataProcessor = dataProcessor;
        this.submitter = submitter;
    }
}
