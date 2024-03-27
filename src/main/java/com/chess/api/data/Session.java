package com.chess.api.data;

import com.chess.api.data.piece.Piece;
import java.util.List;
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

    private long whiteUserId;

    private long blackUserId;

    // List of (custom) chess notation action strings (i.e. Piece & Location to Piece & Location)
    private List<String> log;

    // List of Pieces on board and where they can move. Game will provide where they can move.
    private List<Piece> pieces;


}
