package com.example.board.repository;

import com.example.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Page<Board> findByTitleContaining(@Param("searchKeyword") String searchKeyword, Pageable pageable);
}