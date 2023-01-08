package com.activemq.service.controller;

import com.activemq.service.publisher.OrderPublisher;
import com.microservice.basedomain.dto.Order;
import com.microservice.basedomain.dto.OrderEvent;
import com.microservice.basedomain.response.ResponseOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/mq/order")
public class OrderMessageQueueController {
    @Autowired
    private OrderPublisher orderPublisher;
    @PostMapping("/place")
    public ResponseEntity<ResponseOrderEntity<OrderEvent>> placeOrder(@RequestBody Order order){
        try {

            ResponseOrderEntity responseOrderEntity = new ResponseOrderEntity();
            OrderEvent orderEvent = new OrderEvent();
            order.setOrderId(UUID.randomUUID().toString());

            orderEvent.setOrder(order);
            orderEvent.setStatus("PENDING");
            orderEvent.setMessage("Order status is in Pending status");

            orderPublisher.sendMessage(orderEvent);

            responseOrderEntity.setEntity(orderEvent);
            responseOrderEntity.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(responseOrderEntity);
        }catch (Exception e){

            //return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
