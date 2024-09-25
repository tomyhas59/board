package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

         return "redirect:/board/list";
    }



    @GetMapping("/board/list")
    public String boardList(Model model){

        model.addAttribute("list",boardService.boardList());

        return "boardList";
    }

    @GetMapping("/board/view") //localhost:8080/board/view?id=1
    public String boardView(Model model, @RequestParam("id") Integer id){

        model.addAttribute("board", boardService.boardView(id));
        return "boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("id") Integer id){

       boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){
            model.addAttribute("board",boardService.boardView(id));
            return "boardModify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board) {

        Board existingBoard = boardService.boardView(id);

        existingBoard.setTitle(board.getTitle());
        existingBoard.setContent(board.getContent());

        boardService.write(existingBoard);

        return "redirect:/board/list";
    }

}
