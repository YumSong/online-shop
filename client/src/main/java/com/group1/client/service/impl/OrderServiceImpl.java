package com.group1.client.service.impl;

import com.group1.client.dao.OrderRepository;
import com.group1.client.service.OrderService;
import com.group1.core.entity.client.Client;
import com.group1.core.entity.order.Order;
import com.group1.core.utils.base.model.Page;
import com.group1.core.utils.base.model.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource(name = "orderRepository")
    private OrderRepository orderRepository;

    @Transactional
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override

    public Integer delete(String orderId) {
        return orderRepository.delete(orderId);
    }

    @Override
    @Transactional
    public Order update(String orderId, Integer status) {
        return orderRepository.update(orderId,status);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }


}
