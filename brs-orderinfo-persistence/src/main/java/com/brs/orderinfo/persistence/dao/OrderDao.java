package com.brs.orderinfo.persistence.dao;


import com.brs.orderinfo.api.domain.OrderDomain;
import org.apache.ibatis.annotations.Param;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
public interface OrderDao {

    /**
     * add new order
     * @param newOrder
     */
    void createNewOrder(@Param("newOrder") OrderDomain newOrder) ;

    /**
     *
     * @param orderId
     * @return
     */
    OrderDomain selectById(@Param("orderId") String orderId);



}
