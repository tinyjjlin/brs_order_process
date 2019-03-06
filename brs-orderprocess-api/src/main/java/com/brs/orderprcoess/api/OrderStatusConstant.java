package com.brs.orderprcoess.api;

/**
 * 订单状态
 * @author tiny lin
 * @date 2019/3/5
 */
public interface OrderStatusConstant {
    String ORDER_STATUS_NEW = "新建";
    String ORDER_STATUS_CONFIRM = "确认";
    String ORDER_STATUS_DISPATCH = "转发";
    String ORDER_STATUS_EDITOR = "撰写中";
    String ORDER_STATUS_CONTRIBUTE = "已投稿";
}
