package com.brs.orderinfo.api;

/**
 * @author tiny lin
 * @date 2019/3/6
 */
public interface OrderService {
    /**
     * create new order
     * @param newOrderDto
     */
    void createNewOrder(Order newOrderDto);

    Order getOrder(String orderId);
}
