package com.chess.api.controller;

import com.chess.api.view.response.SessionView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/sessions/")
    public SessionView handleGetSession() {
        return null;
    }

    @PutMapping("/sessions/")
    public SessionView handleCreateSession(){
        return null;
    }

    /**
     * Update the session by performing an action on the board. A session can only be updated
     * while it is currently in-progress, by authorized players, and by the player allowed to act,
     * and once an action causes checkmate or stalemate this session can no longer be updated.
     *
     * @return Information of a session: Players, Board, and history of actions.
     */
    @PostMapping("/sessions/")
    public SessionView handleUpdateSession() {
        return null;
    }

}
