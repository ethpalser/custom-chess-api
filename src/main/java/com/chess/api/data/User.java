package com.chess.api.data;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Document("user")
public class User {

    @Id
    private ObjectId id;

    private String username;

    private String password;

    // A generated token for auth, so login is not necessary for future requests
    private String token;

    // A time period that this token expires
    private LocalDateTime tokenExpire;

    public User() {
        this.id = new ObjectId();
        this.username = "Test";
        this.password = "Test";
        this.token = null;
        this.tokenExpire = null;
    }

    public User(String username, String password) {
        this.id = new ObjectId();
        this.username = username;
        this.password = password;
        this.token = null;
        this.tokenExpire = null;
    }

}
