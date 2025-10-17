package com.example.boardservice.domain.boards.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardsUpdateRequest {
    private String title;
    private String content;
}
