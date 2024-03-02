package com.example.account.listener;

import com.example.account.dto.response.UserResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.List;

public class UserQueryListener {
    @RabbitListener(queues = "murat.queue")
    public void handleUsers(List<UserResponse> users) {
        System.out.println(users);
    }
}