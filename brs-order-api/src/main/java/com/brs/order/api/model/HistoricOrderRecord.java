package com.brs.order.api.model;

import com.brs.order.api.Order;
import lombok.Data;

/**
 * @author tiny lin
 * @date 2019/2/26
 */
@Data
public class HistoricOrderRecord {
    /**
     * 订单状态
     */
    private Object orderStatus="";
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

    private Order orderInfo;
    public HistoricOrderRecord(){}
    public HistoricOrderRecord(Object orderStatus, Object editor, Object dataProcessor, Object submitter, Order orderInfo){
        this.orderStatus = orderStatus;
        this.editor = editor;
        this.dataProcessor = dataProcessor;
        this.submitter = submitter;
        this.orderInfo  = orderInfo;
    }
}
