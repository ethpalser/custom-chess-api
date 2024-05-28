package com.chess.api.data;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@NonNull
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

}
