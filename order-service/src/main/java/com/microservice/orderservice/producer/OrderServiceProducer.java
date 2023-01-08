package com.microservice.orderservice.producer;

import com.microservice.basedomain.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceProducer {

    private static  final Logger looger = LoggerFactory.getLogger(OrderServiceProducer.class);
    @Autowired
    private KafkaTemplate<String,OrderEvent> kafkaTemplate;

    @Autowired
    private NewTopic topic;

    public void sendMessage(OrderEvent orderEvent){
        looger.info(String.format("Order Event Message -> %s",orderEvent.toString()));
        Message<OrderEvent> message = MessageBuilder.withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC,topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
