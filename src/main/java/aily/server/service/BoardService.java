package aily.server.service;

import aily.server.DTO.BoardDTO;
import aily.server.entity.redict;
import aily.server.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    //사진 경로 위치
    private static final String IMAGE_DIRECTORY = "/home/ubuntu/category/";

    private final Map<String, String> category = new HashMap<String, String>() {{
        put("gen", "1");
        put("can", "2");
        put("pet", "3");
        put("paper", "4");
        put("glass", "5");
        put("vinyl", "6");
        put("plastic", "7");
        put("food", "8");
        put("cloth", "9");
    }};

    private final BoardRepository boardRepository;

//    //글작성처리
//    public void write(redict board ){
//        boardRepository.save(board);
//    }
//
//    /*검색기능-2*/
//    //검색
//    public List<redict> boardSearchList(String searchKeyword){
//        return boardRepository.findByTitleContaining(searchKeyword);
//    }

//    //특정 게시글 불러오기
//    public BoardDTO boardview(Long id){
//        Optional<redict> dict = boardRepository.findById(id); //어떤게시글을 불러올지 지정을해주어야한다 (Long 값으로)
//        if(dict.isPresent()){
//            BoardDTO boardDTO = BoardDTO.toBoardDTO(dict.get());
//            return boardDTO;
//        }
//        BoardDTO dto = new BoardDTO();
//        dto.setTitle("확인되지 않는 사전입니다.");
//        return dto;
//    }

    //카테고리 별 게시글 불러오기
    public List<BoardDTO> boardviewcategory(String number){

        String categoryname = category.get(number);

        return BoardDTO.toListBoardDTO(boardRepository.findByCategory(categoryname));

    }

    //카테고리 별 사진 불러오기
    public ResponseEntity<ByteArrayResource> CategoryGetImage(String id) throws IOException {
    String imagePath = IMAGE_DIRECTORY + id + ".png";
    Path path = Paths.get(imagePath);
    byte[] imageBytes = Files.readAllBytes(path);

    ByteArrayResource resource = new ByteArrayResource(imageBytes);

    CacheControl cacheControl = CacheControl.noCache();

        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .contentLength(imageBytes.length)
                .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
            .body(resource);
}

//    //특정게시글삭제
//    public void boardDelete(Long id){ /*id값 1번을 넣어주면 1번을 삭제한다*/
//        boardRepository.deleteById(id);
//    }
//
//public Map<String, List<redict>> findAll() {
//    List<redict> entities = boardRepository.findAll();
//
//
//    System.out.println(entities.get(1).getNumber());
//    System.out.println(" 크기 :: " + entities.size());
//
//    return datalist;
//}



//    public void update(Long id, redict board){
//        Optional<redict> dict = boardRepository.findById(id);
//        if(dict.isPresent()){
//            redict chdict = dict.get();
//            chdict.setNumber(board.getNumber());
//            chdict.setTitle(board.getTitle());
//            chdict.setImgfile(board.getImgfile());
//            chdict.setContent(board.getContent());
//            boardRepository.save(chdict);
//        }
//    }
}