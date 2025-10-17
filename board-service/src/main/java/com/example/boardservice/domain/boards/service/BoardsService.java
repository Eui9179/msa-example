package com.example.boardservice.domain.boards.service;

import com.example.boardservice.domain.boards.entity.Boards;
import com.example.boardservice.domain.boards.repository.BoardsRepository;
import com.example.boardservice.domain.boards.web.dto.BoardsResponse;
import com.example.boardservice.domain.boards.web.dto.BoardsSaveRequest;
import com.example.boardservice.domain.boards.web.dto.BoardsUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardsService {

    private final BoardsRepository boardsRepository;

    public Long save(BoardsSaveRequest request) {
        return boardsRepository.save(request.toEntity()).getId();
    }

    public BoardsResponse findById(Long id) {
        return BoardsResponse.from(getBoard(id));
    }

    @Transactional
    public Long update(Long id, BoardsUpdateRequest request) {
        Boards board = getBoard(id);
        board.update(request.getTitle(), request.getContent());
        return id;
    }

    private Boards getBoard(Long id) {
        return boardsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Post - " + id));
    }
}
