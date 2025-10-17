package com.example.boardservice.domain.boards.web.dto;

import com.example.boardservice.domain.boards.entity.Boards;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardsSaveRequest {
    private String title;
    private String content;
    private String author;

    public Boards toEntity() {
        return Boards.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
