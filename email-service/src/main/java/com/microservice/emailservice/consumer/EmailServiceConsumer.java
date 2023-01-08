package com.microservice.emailservice.consumer;

import com.microservice.basedomain.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceConsumer {

    Logger logger = LoggerFactory.getLogger(EmailServiceConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent event){
        logger.info(String.format("Consume Order Service in Email Service-> %s",event.toString()));
    }

}
