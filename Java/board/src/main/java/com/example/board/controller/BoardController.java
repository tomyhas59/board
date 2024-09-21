package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {


    @Autowired
    private BoardService boardService;
    @GetMapping("/board/write") //localhost:8080/board/write
    public String boardWriteForm() {
        return "boardWrite"; //boardWrite.html 문서 렌더링
    }

    @PostMapping("/board/writepro")
    public  String boardWritePro(Board board){

          boardService.write(board);

         return "";
    }
    @GetMapping("/board/list")
    public String boardList(Model model){

        model.addAttribute("list",boardService.boardList());

        return "boardList";
    }


}
