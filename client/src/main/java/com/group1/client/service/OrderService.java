package com.group1.client.service;

import com.group1.core.entity.client.Client;
import com.group1.core.entity.order.Order;
import com.group1.core.utils.base.model.Page;
import com.group1.core.utils.base.model.Pageable;

public interface OrderService {
    Order save(Order order);
    Integer delete(String orderId);
    Order update(String orderId,Integer status);
    Page<Order> findAll(Pageable pageable);
}