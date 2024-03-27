package com.chess.api.data;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@NonNull
@Document("user")
public class User {

    @Id
    private long id;

    private String username;

    private String password;

    // A generated token for auth, so login is not necessary for future requests
    private String token;

    // A time period that this token expires
    private LocalDateTime tokenExpire;

}
