package com.chess.api.data;

import com.chess.api.data.piece.Action;
import com.chess.api.data.piece.Piece;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Document("session")
public class Session {

    @Id
    private ObjectId id;

    // Not using @DBRef as user ids are only needed for authorization
    private ObjectId userWhiteId;

    private ObjectId userBlackId;

    // Nullable
    private ObjectId userWinnerId;

    private boolean inProgress;

    // List of (custom) chess notation action strings (i.e. Piece & Location to Piece & Location)
    private List<Action> moves;

    // List of Pieces on board and where they can move. Game will provide where they can move.
    private List<Piece> pieces;

    public Session() {
        this.id = new ObjectId();
        this.userWhiteId = null;
        this.userBlackId = null;
        this.userWinnerId = null;
        this.inProgress = false;
        this.moves = List.of();
        this.pieces = List.of();
    }

}
