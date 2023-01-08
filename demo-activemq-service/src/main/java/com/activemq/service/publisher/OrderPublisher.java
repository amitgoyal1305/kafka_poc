package com.activemq.service.publisher;

import com.microservice.basedomain.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderPublisher {

    Logger logger = LoggerFactory.getLogger(OrderPublisher.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${spring.activemq.name}")
    private String queueName;


    public void sendMessage(OrderEvent orderEvent){
        jmsTemplate.convertAndSend(queueName,orderEvent);
        logger.info(String.format("Send Order Service in Email Service-> %s",orderEvent.toString()));
    }
}
