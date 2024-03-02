package com.example.account.controller;

import com.example.account.dto.response.UserResponse;
import com.example.account.model.User;
import com.example.account.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/add")
    public ResponseEntity<UserResponse> add(@RequestBody User request){
        UserResponse response = userService.add(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> get(){
        List<UserResponse> response = userService.get();
        return ResponseEntity.ok(response);
    }
}

