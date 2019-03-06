package com.brs.order.service;


import com.brs.order.api.Order;
import com.brs.order.api.OrderService;
import com.brs.order.api.dto.ClientOrderDTO;
import com.brs.order.api.dto.ManagerOrderDTO;
import com.brs.order.api.dto.StaffOrderDTO;
import com.brs.order.persistence.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired(required = false)
    private OrderMapper orderMapper;


    @Override
    public void createNewOrder(Order newOrderDto) {
        orderMapper.createNewOrder(newOrderDto);
    }

    @Override
    public ClientOrderDTO getClientOrder(String orderId) {
        return orderMapper.selectClientOrderById(orderId);
    }

    @Override
    public ManagerOrderDTO getManagerOrder(String orderId) {
        return orderMapper.selectManagerOrderById(orderId);
    }

    @Override
    public Order getOrder(String orderId) {
        return orderMapper.selectById(orderId);
    }

    @Override
    public StaffOrderDTO getStaffOrder(String orderId) {
      return orderMapper.selectStaffOrderById(orderId);
    }


}
