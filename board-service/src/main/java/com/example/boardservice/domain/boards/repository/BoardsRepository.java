package com.example.boardservice.domain.boards.repository;

import com.example.boardservice.domain.boards.entity.Boards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardsRepository extends JpaRepository<Boards, Long> {
}
