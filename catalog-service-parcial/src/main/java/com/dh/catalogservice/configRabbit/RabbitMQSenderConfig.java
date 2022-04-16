package com.dh.catalogservice.configRabbit;


import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQSenderConfig {

    @Value("${queue.catalog.name}")
    public String catalogQueue;

    @Bean
    public Queue queue(){
        return new Queue(this.catalogQueue, true);
    }

}
