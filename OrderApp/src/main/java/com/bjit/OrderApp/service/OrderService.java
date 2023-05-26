package com.bjit.OrderApp.service;

import com.bjit.OrderApp.entity.OrderEntity;
import com.bjit.OrderApp.model.OrderRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    ResponseEntity<Object> getOrder(Long id);
    ResponseEntity<Iterable<OrderEntity>> getOrderByPayment(Long pay);
    ResponseEntity<Iterable<OrderEntity>> getAllOrders();
    ResponseEntity<Object> createOrder(OrderRequestModel requestModel);
    ResponseEntity<Object> updateOrder(Long id, OrderRequestModel requestModel);
    ResponseEntity<Object> deleteOrder(Long id);

}
