package com.chess.api.service;

import com.chess.api.dao.UserRepository;
import com.chess.api.data.User;
import com.chess.api.view.request.UserCreateRequest;
import com.chess.api.view.request.UserLoginRequest;
import com.chess.api.view.request.UserUpdateRequest;
import com.chess.api.view.response.LoginView;
import com.chess.api.view.response.PlayerView;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchAllUsers() {
        return this.userRepository.findAll();
    }

    public LoginView login(UserLoginRequest request) {
        // Todo
        return null;
    }

    public User createUser(UserCreateRequest request) {
        User user = new User(request.getUsername(), request.getPassword());
        return this.userRepository.save(user);
    }

    public User getUser(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    public User updateUser(String originalUsername, UserUpdateRequest request) {
        User user = getUser(originalUsername);
        user.setUsername(request.getNewUsername());
        return this.userRepository.save(user);
    }

}
