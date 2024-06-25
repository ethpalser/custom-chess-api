package com.chess.api.view.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorView {

    private String error;
    private String code;
    private String message;
    private String path;
    private LocalDateTime timestamp;

}
