package com.activemq.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJcaListenerContainerFactory;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class OrderJMSConfig {


    @Bean
    public DefaultJmsListenerContainerFactory defaultJcaListenerContainerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory jmsDefaultJmsListenerContainerFactory =
                    new DefaultJmsListenerContainerFactory();
        jmsDefaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        jmsDefaultJmsListenerContainerFactory.setConcurrency("5-10");
        return jmsDefaultJmsListenerContainerFactory;
    }
}
