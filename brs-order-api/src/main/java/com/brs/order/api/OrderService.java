package com.brs.order.api;


import com.brs.order.api.dto.ClientOrderDTO;
import com.brs.order.api.dto.ManagerOrderDTO;
import com.brs.order.api.dto.StaffOrderDTO;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
public interface OrderService {
    /**
     * create new order
     * @param newOrderDto
     */
    void createNewOrder(Order newOrderDto);

    ClientOrderDTO getClientOrder(String orderId);
    ManagerOrderDTO getManagerOrder(String orderId);
    StaffOrderDTO getStaffOrder(String orderId);

    Order getOrder(String orderId);

}
