package com.chess.api.controller;

import com.chess.api.data.SessionStatus;
import com.chess.api.view.request.SessionCreateRequest;
import com.chess.api.view.request.SessionUpdateRequest;
import com.chess.api.view.response.SessionView;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/sessions/")
    public SessionView handleGetSessions(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String username
    ) {
        return null;
    }

    @PutMapping("/sessions/")
    public SessionView handleCreateSession(SessionCreateRequest request) {
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
    public SessionView handleUpdateSession(SessionUpdateRequest request) {
        return null;
    }

    @GetMapping("/sessions/{id}")
    public SessionView handleFetchSession(@PathVariable String id) {
        return null;
    }

    @DeleteMapping("/sessions/{id}")
    public void handleSoftDeleteSession(@PathVariable String id) {
        // empty
    }

}
