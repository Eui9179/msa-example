package com.example.boardservice.domain.boards.web;

import com.example.boardservice.domain.boards.service.BoardsService;
import com.example.boardservice.domain.boards.web.dto.BoardsResponse;
import com.example.boardservice.domain.boards.web.dto.BoardsSaveRequest;
import com.example.boardservice.domain.boards.web.dto.BoardsUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("/api/v1")
public class BoardsApiController {

    private final BoardsService boardsService;

    @GetMapping("/boards")
    public Long save(@RequestBody BoardsSaveRequest request) {
        return boardsService.save(request);
    }

    @GetMapping("/board/{id}")
    public BoardsResponse findById(@PathVariable Long id) {
        return boardsService.findById(id);
    }

    @PutMapping("/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardsUpdateRequest request) {
        return boardsService.update(id, request);
    }
}
