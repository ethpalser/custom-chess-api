package com.chess.api.view.request;

import com.chess.api.data.piece.Action;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SessionUpdateRequest {

    private final Action action;

}
