package com.example.account.producer;

import com.example.account.RabbitMqConfig;
import com.example.account.dto.response.UserResponse;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RabbitMqService {

    private final AmqpTemplate rabbitTemplate;

    private final RabbitMqConfig config;

    public RabbitMqService(AmqpTemplate rabbitTemplate, RabbitMqConfig config) {
        this.rabbitTemplate = rabbitTemplate;
        this.config = config;
    }


    public void send(List<UserResponse> users) {
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), users);
    }
}
