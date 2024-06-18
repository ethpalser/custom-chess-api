package com.chess.api.controller;

import com.chess.api.view.request.UserCreateRequest;
import com.chess.api.view.request.UserLoginRequest;
import com.chess.api.view.request.UserUpdateRequest;
import com.chess.api.view.response.LoginView;
import com.chess.api.view.response.PlayerView;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users/")
    public List<PlayerView> handleFetchAllUsers() {
        return List.of();
    }

    @PostMapping("/users/")
    public LoginView handleLogin(UserLoginRequest request) {
        return null;
    }

    @PutMapping("/users/")
    public PlayerView handleCreateUser(UserCreateRequest request) {
        return null;
    }

    @GetMapping("/users/{username}")
    public PlayerView handleFetchUser(@PathVariable String username) {
        return null;
    }

    @PostMapping("/users/{username}")
    public PlayerView handleUpdateUser(@PathVariable String username, UserUpdateRequest request) {
        return null;
    }

}
