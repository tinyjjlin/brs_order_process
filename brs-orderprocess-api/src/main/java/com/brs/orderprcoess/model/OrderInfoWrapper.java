package com.brs.orderprcoess.model;
import com.brs.orderinfo.api.Order;
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
