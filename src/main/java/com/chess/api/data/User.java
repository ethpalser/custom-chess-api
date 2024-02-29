package com.chess.api.data;

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

}
