package com.example.boardservice.domain.boards.web.dto;

import com.example.boardservice.domain.boards.entity.Boards;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardsResponse {
    private String title;
    private String content;
    private String author;

    public static BoardsResponse from(Boards board) {
        return BoardsResponse.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getAuthor())
                .build();
    }
}
