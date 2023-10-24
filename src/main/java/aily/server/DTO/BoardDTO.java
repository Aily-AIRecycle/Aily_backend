package aily.server.DTO;


import aily.server.entity.redict;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String number;
    private String title;
    private String content;
    private String imgfile;

    public static BoardDTO toBoardDTO(redict board){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setNumber(board.getNumber());
        boardDTO.setTitle(board.getTitle());
        boardDTO.setContent(board.getContent());
        boardDTO.setImgfile(board.getImgfile());
        return boardDTO;
    }

    //카테고리 Entity List에서 UserDTO List로 변환
    public static List<BoardDTO> toListBoardDTO(List<redict> boards) {
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for (redict board : boards) {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setId(board.getId());
            boardDTO.setNumber(board.getNumber());
            boardDTO.setTitle(board.getTitle());
            boardDTO.setContent(board.getContent());
            boardDTO.setImgfile(board.getImgfile());

            boardDTOList.add(boardDTO);
        }

        return boardDTOList;
    }


}