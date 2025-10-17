package com.example.boardservice.domain.boards.repository;


import com.example.boardservice.domain.boards.entity.Boards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BoardsRepositoryTest {

    @Autowired
    BoardsRepository boardsRepository;

    @Test
    void testSave() {
        String title = "Title";
        String content = "Content";

        boardsRepository.save(Boards.builder()
                .title(title)
                .content(content)
                .author("test")
                .build()
        );

        List<Boards> all = boardsRepository.findAll();

        Boards board = all.get(0);
        Assertions.assertThat(board.getTitle()).isEqualTo(title);
        Assertions.assertThat(board.getContent()).isEqualTo(content);
    }
}
