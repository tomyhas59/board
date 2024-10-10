package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {


    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //localhost:8080/board/write
    public String boardWriteForm() {
        return "boardWrite"; //boardWrite.html 문서 렌더링
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, @RequestParam("file") MultipartFile file) throws Exception {

        boardService.write(board, file);

        model.addAttribute("message", "글 작성 완료");
        model.addAttribute("searchUrl", "/board/list");

        return "redirect:/board/list";
    }


    @GetMapping("/board/list")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
        Page<Board> list;

        if (searchKeyword == null || searchKeyword.isEmpty()) {
            list = boardService.boardList(pageable);
        } else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }
        boolean isEmpty = list.isEmpty();
        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 3, 1);
        int endPage = Math.min(nowPage + 4, list.getTotalPages());

        model.addAttribute("list", list); // html에 list라는 이름으로 전달
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("isEmpty", isEmpty);
        return "boardList";
    }

    @GetMapping("/board/view") //localhost:8080/board/view?id=1
    public String boardView(Model model, @RequestParam("id") Integer id) {

        model.addAttribute("board", boardService.boardView(id));
        return "boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("id") Integer id) {

        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardModify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model, @RequestParam("file") MultipartFile file) throws Exception {

        Board existingBoard = boardService.boardView(id);

        existingBoard.setTitle(board.getTitle());
        existingBoard.setContent(board.getContent());

        boardService.write(existingBoard, file);

        model.addAttribute("message", "글 수정 완료");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

}
