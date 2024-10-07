package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired //
    private BoardRepository boardRepository;

    //글 작성
    public void write(Board board, @RequestParam("file") MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "\\Java\\board\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String filename = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, filename);

        file.transferTo(saveFile);

        board.setFilename(filename);
        board.setFilepath("/files/" + filename);

        boardRepository.save(board);
    }

    //게시글 리스트 get
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    //특정 게시글 불러오기

    public Board boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    //게시글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }
}
