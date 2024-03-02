package com.example.account.service;

import com.example.account.dto.response.UserResponse;
import com.example.account.model.User;
import com.example.account.producer.RabbitMqService;
import com.example.account.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
   private final UserRepository repository;
   private final RabbitMqService rabbit;


    public UserService(UserRepository repository, RabbitMqService rabbit) {
        this.repository = repository;
        this.rabbit = rabbit;
    }

    public UserResponse add(User user){
        return userModelToUserResponseDto(repository.save(user));
    }
    public List<UserResponse> get(){
        List<UserResponse> userResponseList = userModelListToUserResponseList(repository.findAll());
        for (int i = 0; i < 100; i++) {
            rabbit.send(userResponseList);
        }
        return userResponseList;
    }

    private List<UserResponse> userModelListToUserResponseList(List<User> users) {
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user:users){
            userResponseList.add(userModelToUserResponseDto(user));
        }

        return userResponseList;
    }

    private UserResponse userModelToUserResponseDto(User user) {
        UserResponse response = new UserResponse();
        response.username = user.getUsername();
        response.storeName = response.username + " Store";
        return response;
    }
}
