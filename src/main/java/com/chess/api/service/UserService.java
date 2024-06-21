package com.chess.api.service;

import com.chess.api.core.exception.UnauthorizedAccessException;
import com.chess.api.core.utils.KeyValuePair;
import com.chess.api.dao.UserRepository;
import com.chess.api.data.User;
import com.chess.api.view.request.UserCreateRequest;
import com.chess.api.view.request.UserLoginRequest;
import com.chess.api.view.request.UserUpdateRequest;
import com.chess.api.view.response.LoginView;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    private UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public List<User> fetchAllUsers() {
        return this.userRepository.findAll();
    }

    @Transactional
    public LoginView login(UserLoginRequest request) throws UnauthorizedAccessException {
        // Todo: Use refreshToken to find user, then return if that user exists and their token has not expired

        User user = userRepository.findUserByUsername(request.getUsername());
        if (user == null) {
            throw new UnauthorizedAccessException();
        }
        String hashedPassword = hashString(request.getPassword());
        if (!hashedPassword.equals(user.getPassword())) {
            throw new UnauthorizedAccessException();
        }

        String refreshToken = this.authService.createRefreshToken();
        user.setToken(refreshToken);
        user.setTokenExpire(LocalDateTime.now().plus(Period.ofMonths(1)));
        user = this.userRepository.save(user);

        String jwt = authService.createJWTWithClaims(Date.from(Instant.now().plus(Period.ofDays(1))),
                KeyValuePair.of("userId", user.getId().toString()));
        return new LoginView(jwt, user.getToken());
    }

    public User createUser(UserCreateRequest request) {
        String hashedPassword = hashString(request.getPassword());
        User user = new User(request.getUsername(), hashedPassword);
        user.setToken(this.authService.createRefreshToken());
        user.setTokenExpire(LocalDateTime.now().plus(Period.ofMonths(1)));
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

    private String hashString(String message) {
        return DigestUtils.sha256Hex(message);
    }
}
