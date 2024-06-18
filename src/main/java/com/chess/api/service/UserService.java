package com.chess.api.service;

import com.chess.api.dao.UserRepository;
import com.chess.api.data.User;
import com.chess.api.view.request.UserCreateRequest;
import com.chess.api.view.request.UserLoginRequest;
import com.chess.api.view.request.UserUpdateRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchAllUsers() {
        return this.userRepository.findAll();
    }

    public User login(UserLoginRequest request) {
        // Todo
        return null;
    }

    public User createUser(UserCreateRequest request) {
        User user = new User(request.getUsername(), request.getPassword());
        return this.userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    public User updateUser(UserUpdateRequest request, String originalUsername) {
        User user = getUserByUsername(originalUsername);
        user.setUsername(request.getNewUsername());
        return this.userRepository.save(user);
    }

}
