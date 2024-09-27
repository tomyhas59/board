package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired //
    private BoardRepository boardRepository;

    //글 작성
    public void write(Board board, @RequestParam("file") MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String filename = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, filename);

        file.transferTo(saveFile);

        board.setFilename(filename);
        board.setFilepath("/files/" + filename);

        boardRepository.save(board);
    }

    //게시글 리스트 get
    public List<Board> boardList() {
        return boardRepository.findAll();
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
