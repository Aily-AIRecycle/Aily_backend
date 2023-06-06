package aily.server.service;

import aily.server.DTO.BoardDTO;
import aily.server.entity.redict;
import aily.server.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired(required = true) //new를 써야하지만, 스프링부트가 알아서 읽어와서 주입을해준다.
    private BoardRepository boardRepository;

    //글작성처리
    public void write(redict board ){


        boardRepository.save(board);
    }

    //게시글리스트처리
    public Page<redict> boardList(Pageable pageable){
        //findAll : 테스트보드라는 클래스가 담긴 List를 반환하는것을 확인할수있다
        return boardRepository.findAll(pageable);
    }

    /*검색기능-2*/
    //검색
    public Page<redict> boardSearchList(String searchKeyword, Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
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
            chdict.setContent(board.getContent());
            chdict.setTitle(board.getTitle());
            boardRepository.save(chdict);
        }
    }
}