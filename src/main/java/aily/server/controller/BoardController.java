package aily.server.controller;

import aily.server.DTO.BoardDTO;
import aily.server.entity.redict;
import aily.server.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/write") //어떤 url로 접근할 것인지 정해주는 어노테이션 //localhost:8080/board/write
    public String boardWriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public ResponseEntity<String> boardWritePro(@RequestBody redict board, Model model){
        boardService.write(board);

        String imagePath = "/var/www/images/" + board.getImgfile();

        model.addAttribute("imgPath", imagePath);
        model.addAttribute("message","글작성이 완료되었습니다");
        model.addAttribute("searchUrl","/board/list");

        return ResponseEntity.ok("글작성이 완료되었습니다");
    }

    @GetMapping("/board/list")
    public ResponseEntity<List> boardList(@RequestBody String searchKeyword){

        if(searchKeyword.equals("")){
            return ResponseEntity.ok(boardService.findAll()); //기존의 리스트보여줌
        }else{
            return ResponseEntity.ok(boardService.boardSearchList(searchKeyword)); //검색리스트반환
        }
    }

    @GetMapping("/board/{id}") //localhost:8080/board/1 //(get방식 uri 파라미터)
    public ResponseEntity<BoardDTO> boardView(@PathVariable Long id){
        return ResponseEntity.ok(boardService.boardview(id));
    }

    @GetMapping("/board/delete/{id}")
    public ResponseEntity<String> boardDelete(@PathVariable Long id){
        boardService.boardDelete(id);
        //게시물삭제하고 게시물리스트로 넘어가야하므로
        return ResponseEntity.ok("삭제완료");
    }

//    //PathVariable이라는 것은 modify 뒤에있는 {id}부분이 인식이되서 Long형태의 id로 들어온다는것 -> 수정페이지 이동
//    @GetMapping("/board/modify/{id}")
//    public ResponseEntity<String> boardModify(@PathVariable("id") Long id){
//
//        //상세페이지에 있는 내용과, 수정페이지의 내용이 같기때문에 위 코드와 같은 것을 확인할수있다
//        model.addAttribute("testboard", boardService.boardview(id));
//
//        return "boardmodify";
//    }

    @PostMapping("/board/update/{id}")
    public ResponseEntity<String> boardUpdate(@PathVariable Long id, @RequestBody redict board) {

        boardService.update(id, board); //추가 → 수정한내용을 boardService의 write부분에 넣기

        return ResponseEntity.ok("update 성공");
    }

//    @GetMapping("/board/view/{id}")
//    public String findById(@PathVariable Long id, Model model){
//        model.addAttribute("testboard", boardService.boardview(id));
//        return "boardview";
//    }
}