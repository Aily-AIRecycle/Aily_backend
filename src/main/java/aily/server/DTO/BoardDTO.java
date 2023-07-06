package aily.server.DTO;


import aily.server.entity.redict;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private int number;
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
}