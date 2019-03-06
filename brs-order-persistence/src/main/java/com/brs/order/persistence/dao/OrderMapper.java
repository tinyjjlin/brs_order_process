package com.brs.order.persistence.dao;


import com.brs.order.api.Order;
import com.brs.order.api.dto.ClientOrderDTO;
import com.brs.order.api.dto.ManagerOrderDTO;
import com.brs.order.api.dto.StaffOrderDTO;
import com.brs.order.persistence.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
public interface OrderMapper {

    /**
     * add new order
     * @param newOrder
     */
    void createNewOrder(@Param("newOrder") Order newOrder) ;

    /**
     *
     * @param orderId
     * @return
     */
    OrderEntity selectById(@Param("orderId")String orderId);

    ClientOrderDTO selectClientOrderById(@Param("orderId")String orderId);
    ManagerOrderDTO selectManagerOrderById(@Param("orderId")String orderId);
    StaffOrderDTO selectStaffOrderById(@Param("orderId")String orderId);


}
