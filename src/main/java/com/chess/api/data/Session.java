package com.chess.api.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("session")
public class Session {

    @Id
    private long id;

    private String board;

    private long whiteUserId;

    private long blackUserId;

}
