package com.microservice.orderservice.controller;

import com.microservice.basedomain.dto.Order;
import com.microservice.basedomain.dto.OrderEvent;
import com.microservice.basedomain.response.ResponseOrderEntity;
import com.microservice.orderservice.producer.OrderServiceProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceProducer orderServiceProducer;
    @PostMapping("/place")
    public ResponseEntity<ResponseOrderEntity<OrderEvent>> placeOrder(@RequestBody Order order){
        ResponseOrderEntity responseOrderEntity = new ResponseOrderEntity();
        OrderEvent orderEvent = new OrderEvent();
        order.setOrderId(UUID.randomUUID().toString());

        orderEvent.setOrder(order);
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order status is in Pending status");

        orderServiceProducer.sendMessage(orderEvent);

        responseOrderEntity.setEntity(orderEvent);
        responseOrderEntity.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(responseOrderEntity);
    }

}
