package aily.server.service;

import aily.server.DTO.BoardDTO;
import aily.server.entity.redict;
import aily.server.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    //글작성처리
    public void write(redict board ){
        boardRepository.save(board);
    }

    /*검색기능-2*/
    //검색
    public List<redict> boardSearchList(String searchKeyword){
        return boardRepository.findByTitleContaining(searchKeyword);
    }

    //특정 게시글 불러오기
    public BoardDTO boardview(Long id){
        Optional<redict> dict = boardRepository.findById(id); //어떤게시글을 불러올지 지정을해주어야한다 (Long 값으로)
        if(dict.isPresent()){
            BoardDTO boardDTO = BoardDTO.toBoardDTO(dict.get());
            return boardDTO;
        }
        BoardDTO dto = new BoardDTO();
        dto.setTitle("확인되지 않는 사전입니다.");
        return dto;
    }

    //특정게시글삭제
    public void boardDelete(Long id){ /*id값 1번을 넣어주면 1번을 삭제한다*/
        boardRepository.deleteById(id);
    }

    public List<BoardDTO> findAll() {
        List<redict> entity = boardRepository.findAll();
        List<BoardDTO> dto = new ArrayList<>();

        entity.forEach(redict -> {
            dto.add(BoardDTO.toBoardDTO(redict));
        });
        return dto;
    }

    public void update(Long id, redict board){
        Optional<redict> dict = boardRepository.findById(id);
        if(dict.isPresent()){
            redict chdict = dict.get();
            chdict.setNumber(board.getNumber());
            chdict.setTitle(board.getTitle());
            chdict.setImgfile(board.getImgfile());
            chdict.setContent(board.getContent());
            boardRepository.save(chdict);
        }
    }
}