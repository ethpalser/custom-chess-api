package com.chess.api.data.piece;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("piece")
public class Piece {

    @Id
    public String code;

    public String name;

    public PieceType type;

    // A point in the format of 'char''num' (ex. a0)
    public Point location;

    // A list of points in the format of 'char''num' (ex. e4). Configuring movements by client currently not supported.
    public List<Point> movements;
}
