package com.restapi.restapiproject.controller;

import com.microservice.basedomain.dto.Order;
import com.microservice.basedomain.dto.OrderEvent;
import com.microservice.basedomain.response.ResponseOrderEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/place")
    public ResponseEntity<ResponseOrderEntity<OrderEvent>> placeOrder(@RequestBody Order order){
        ResponseOrderEntity responseOrderEntity = new ResponseOrderEntity();
        OrderEvent orderEvent = new OrderEvent();
        order.setOrderId(UUID.randomUUID().toString());

        orderEvent.setOrder(order);
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order status is in Pending status");


        responseOrderEntity.setEntity(orderEvent);
        responseOrderEntity.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(responseOrderEntity);
    }

    @GetMapping("/place")
    public ResponseEntity<ResponseOrderEntity<OrderEvent>> placeOrder(){

        ResponseOrderEntity responseOrderEntity = new ResponseOrderEntity();
        OrderEvent orderEvent = new OrderEvent();
        Order order = new Order(null,"Amit" ,20,40.6);
        order.setOrderId(UUID.randomUUID().toString());

        orderEvent.setOrder(order);
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order status is in Pending status");


        responseOrderEntity.setEntity(orderEvent);
        responseOrderEntity.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(responseOrderEntity);
    }

}
