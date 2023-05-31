package aily.server.service;

import aily.server.entity.testboard;
import aily.server.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired(required = true) //new를 써야하지만, 스프링부트가 알아서 읽어와서 주입을해준다.
    private BoardRepository boardRepository;

    //글작성처리
    public void write(testboard board ) throws Exception{

        //파일 저장
        boardRepository.save(board);
    }

    //게시글리스트처리
    public Page<testboard> boardList(Pageable pageable){
        //findAll : 테스트보드라는 클래스가 담긴 List를 반환하는것을 확인할수있다
        return boardRepository.findAll(pageable);
    }

    /*검색기능-2*/
    //검색
    public Page<testboard> boardSearchList(String searchKeyword, Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    //특정 게시글 불러오기
    public testboard boardview(int id){
        return boardRepository.findById(id).get(); //어떤게시글을 불러올지 지정을해주어야한다 (Integer값으로)
    }

    //특정게시글삭제
    public void boardDelete(Integer id){ /*id값 1번을 넣어주면 1번을 삭제한다*/
        boardRepository.deleteById(id);
    }

}
