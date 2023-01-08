package com.activemq.consumer.service;

import com.microservice.basedomain.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumerService {
    private final Logger logger = LoggerFactory.getLogger(OrderConsumerService.class);


    @JmsListener(destination = "${spring.activemq.name}")
    public void consumeMessage(OrderEvent orderEvent){
        logger.info(String.format("Consume Order Service in Email Service-> %s",orderEvent.toString()));
    }
}
