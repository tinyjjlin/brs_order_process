package com.brs.order.api.dto;

import com.brs.order.api.Order;
import com.brs.order.api.model.OrderDescription;
import com.brs.order.api.model.TaskInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tiny lin
 * @date 2019/2/27
 */
@Data
public class OrderInfoWrapper implements Serializable {
    private Order orderInfo;
    private OrderDescription orderDesc;
    private TaskInfo taskInfo;

    public OrderInfoWrapper(){}
    public OrderInfoWrapper(Order orderInfo, OrderDescription orderDesc, TaskInfo taskInfo){
        this.orderInfo = orderInfo;
        this.orderDesc = orderDesc;
        this.taskInfo = taskInfo;
    }
}
